head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ErrorCatalog.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �G���[�����Ǘ�����N���X(WEB3ErrorCatalog.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * �G���[�����Ǘ�����N���X�B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public final class WEB3ErrorCatalog
{

    /**
     * �G���[�}�l�[�W���B
     */
    private static ErrorManager errorMgr = ErrorManager.getInstance(WEB3ErrorCatalog.class);


    /**
     * �v���I�ȃV�X�e���G���[�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80002 = errorMgr.defineErrorInfo(
            80002,
            "SYSTEM_ERROR_80002", 
            "�\�����Ȃ��V�X�e���G���[���������܂����B");

    /**
     * DB�A�N�Z�X�G���[�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80003 = errorMgr.defineErrorInfo(
            80003,
            "SYSTEM_ERROR_80003", 
            "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");

    /**
     * �Y���f�[�^�d���B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80004 = errorMgr.defineErrorInfo(
            80004,
            "SYSTEM_ERROR_80004", 
            "�e�[�u���ɏd������Y���f�[�^�����݂��܂��B");

    /**
     * �Y���f�[�^�Ȃ��B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80005 = errorMgr.defineErrorInfo(
            80005,
            "SYSTEM_ERROR_80005", 
            "�e�[�u���ɊY������f�[�^������܂���B");

    /**
     * �f�[�^�s�����G���[�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80006 = errorMgr.defineErrorInfo(
            80006,
            "SYSTEM_ERROR_80006", 
            "�f�[�^�s�����G���[�B");

    /**
     * �o�b�`�������B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80007 = errorMgr.defineErrorInfo(
            80007,
            "SYSTEM_ERROR_80007", 
            "�o�b�`�������B");

    /**
     * �ً}��~���B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80008 = errorMgr.defineErrorInfo(
            80008,
            "SYSTEM_ERROR_80008", 
            "�ً}��~���B");

    /**
     * ������ԊO�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80014 = errorMgr.defineErrorInfo(
            80014,
            "SYSTEM_ERROR_80014", 
            "������ԊO�B");

    /**
     * ��t���ԃG���[�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80015 = errorMgr.defineErrorInfo(
            80015,
            "SYSTEM_ERROR_80015", 
            "��t���ԃG���[�B");

    /**
     *  �V�X�e�������~�G���[�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80016 = errorMgr.defineErrorInfo(
            80016,
            "SYSTEM_ERROR_80016", 
            "�V�X�e�������~�G���[�B");

    /**
     * �p�����[�^�l�s���B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80017 = errorMgr.defineErrorInfo(
            80017,
            "SYSTEM_ERROR_80017", 
            "�p�����[�^�l�s���B");

    /**
     * �p�����[�^�^�C�v�s���B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80018 = errorMgr.defineErrorInfo(
            80018,
            "SYSTEM_ERROR_80018", 
            "�p�����[�^�^�C�v�s���B");

    /**
     * ���׍s������̗v�f���ƃJ�����w�b�_[]�̗v�f���������łȂ��B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80022 = errorMgr.defineErrorInfo(
            80022,
            "SYSTEM_ERROR_80022", 
            "���׍s������̗v�f���ƃJ�����w�b�_[]�̗v�f���������łȂ��B");

    /**
     * ���ڂ̌^���ϊ��ł��Ȃ��B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80023 = errorMgr.defineErrorInfo(
            80023,
            "SYSTEM_ERROR_80023", 
            "���ڂ̌^���ϊ��ł��Ȃ��B");

    /**
     * �����ΏۊO�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80025 = errorMgr.defineErrorInfo(
            80025,
            "SYSTEM_ERROR_80025", 
            "�����ΏۊO�B");

    /**
     * ���{���\�߃G���[���b�Z�[�W�̔ԃi���o�[�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80026 = errorMgr.defineErrorInfo(
            80026,
            "SYSTEM_ERROR_80026", 
            "���{���\�߃G���[���b�Z�[�W�̔ԃi���o�[�B");

    /**
     * ���{���\�߃G���[���b�Z�[�W�̔ԃi���o�[�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80075 = errorMgr.defineErrorInfo(
            80075,
            "SYSTEM_ERROR_80075", 
            "���{���\�߃G���[���b�Z�[�W�̔ԃi���o�[�B");

    /**
     * �������b�N���s�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80076 = errorMgr.defineErrorInfo(
            80076,
            "SYSTEM_ERROR_80076", 
            "�������b�N�Ɏ��s���܂����B");

    /**
     * �����ʒm�����G���[�i�V�X�e���G���[�j�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80077 = errorMgr.defineErrorInfo(
            80077,
            "SYSTEM_ERROR_80077", 
            "�����ʒm���������s���܂����i�V�X�e���G���[�j�B");

    /**
     * ����ʒm�����G���[�i�V�X�e���G���[�j�B<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80078 = errorMgr.defineErrorInfo(
            80078,
            "SYSTEM_ERROR_80078", 
            "����ʒm���������s���܂����i�V�X�e���G���[�j�B");

    /**
     * �Y���s��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00003 = errorMgr.defineErrorInfo(
            3,
            "BUSINESS_ERROR_00003", 
            "�����ɊY������s�ꂪ�݂���܂���ł����B");

    /**
     * �����K�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00004 = errorMgr.defineErrorInfo(
            4,
            "BUSINESS_ERROR_00004", 
            "���͂��ꂽ�����͌��݁A�����K�����ƂȂ��Ă���܂��B");

    /**
     * ��������ɂĎ戵�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00005 = errorMgr.defineErrorInfo(
            5,
            "BUSINESS_ERROR_00005", 
            "���͂��ꂽ�����́A��������ł̂��戵�͂ł��܂���B");

    /**
     * �}�[�P�b�g���C�N��������舵���Ȃ����X�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00006 = errorMgr.defineErrorInfo(
            6,
            "BUSINESS_ERROR_00006", 
            "���݂��������Ă��镔�X�ł́A�}�[�P�b�g���C�N�����̂��戵�͂ł��܂���B");

    /**
     * ����p�X���[�h�s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00009 = errorMgr.defineErrorInfo(
            9,
            "BUSINESS_ERROR_00009", 
            "����p�X���[�h���s���ł��B");

    /**
     * �v���I�ȋƖ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00010 = errorMgr.defineErrorInfo(
            10,
            "BUSINESS_ERROR_00010", 
            "�\�����Ȃ��Ɩ��G���[���������܂����B");

    /**
     * �o�b�`�������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00011 = errorMgr.defineErrorInfo(
            11,
            "BUSINESS_ERROR_00011", 
            "�V�X�e�����o�b�`�������ł��B");

    /**
     * �ً}��~���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00012 = errorMgr.defineErrorInfo(
            12,
            "BUSINESS_ERROR_00012", 
            "�V�X�e�����ً}��~���ł��B");

    /**
     * ��t�\���ԊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00013 = errorMgr.defineErrorInfo(
            13,
            "BUSINESS_ERROR_00013", 
            "�V�X�e������t�\���ԊO�ł��B");

    /**
     * ��~���i���ЋK���j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00014 = errorMgr.defineErrorInfo(
            14,
            "BUSINESS_ERROR_00014", 
            "���͂��ꂽ�����́A���ЋK���ɂ�蔄����~���ƂȂ��Ă���܂��B");

    /**
     * ��~���i������K���j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00015 = errorMgr.defineErrorInfo(
            15,
            "BUSINESS_ERROR_00015", 
            "���͂��ꂽ�����́A������K���ɂ�蔄����~���ƂȂ��Ă���܂��B");

    /**
     * ���s�����̒l���s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00019 = errorMgr.defineErrorInfo(
            19,
            "BUSINESS_ERROR_00019", 
            "���s�����̒l���s���ł��B");

    /**
     * ����������J�݂��Ă��Ȃ��ڋq����������w�肵���ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00026 = errorMgr.defineErrorInfo(
            26,
            "BUSINESS_ERROR_00026", 
            "���q�l�͓���������J�݂���Ă���܂���B");

    /**
     * �Ēl�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00030 = errorMgr.defineErrorInfo(
            30,
            "BUSINESS_ERROR_00030", 
            "�w�肳�ꂽ�Ēl������������܂���B");

    /**
     * �l���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00031 = errorMgr.defineErrorInfo(
            31,
            "BUSINESS_ERROR_00031", 
            "���͂��ꂽ�����P�����l���͈͓̔��ɂ���܂���B");

    /**
     * ������Ԃ��K�؂ł͂Ȃ���Ԃ̏ꍇ�̗�O�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00032 = errorMgr.defineErrorInfo(
            32,
            "BUSINESS_ERROR_00032", 
            "��������t�����Ԃł͂���܂���B");

    /**
     * �������̊��������������������ꍇ�̗�O�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00036 = errorMgr.defineErrorInfo(
            36,
            "BUSINESS_ERROR_00036", 
            "���q�l�̓��͂��ꂽ�����́A�������̊�����葽�����ߎ󂯕t���邱�Ƃ��o���܂���B");

    /**
     * ���������ꕔ�o���̏�ԂŁA������������萔�ȉ��̏ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00037 = errorMgr.defineErrorInfo(
            37,
            "BUSINESS_ERROR_00037", 
            "���q�l�̓��͂��ꂽ�����́A���������ꕔ�o���̏�ԂŁA��萔�ȉ��̂��ߎ󂯕t���邱�Ƃ��o���܂���B");

    /**
     * �����ƒP���i�w�l�j�������ɒ�������Ă���ꍇ�̗�O�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00038 = errorMgr.defineErrorInfo(
            38,
            "BUSINESS_ERROR_00038", 
            "���Y�s��ł́A�����ƒP���i�w�l�j�������ɒ������邱�Ƃ͏o���܂���B");

    /**
     * ���������牽�������������Ă��Ȃ��ꍇ�̗�O�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00039 = errorMgr.defineErrorInfo(
            39,
            "BUSINESS_ERROR_00039", 
            "�������ꂽ���ڂ��P������܂���B");

    /**
     * �\�[�g���ڂ��s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00055 = errorMgr.defineErrorInfo(
            55,
            "BUSINESS_ERROR_00055", 
            "�\�[�g���ڂ��s���ł��B");

    /**
     * ����Ԃ̒l�����w��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00062 = errorMgr.defineErrorInfo(
            62,
            "BUSINESS_ERROR_00062", 
            "����Ԃ̒l�����w��ł��B");

    /**
     * ���X�̊Ǘ������戵���戵�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00063 = errorMgr.defineErrorInfo(
            63,
            "BUSINESS_ERROR_00063", 
            "���X�̊Ǘ������戵���戵�s�ł��B");

    /**
     * ��������J�݂̐ŋ敪�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00064 = errorMgr.defineErrorInfo(
            64,
            "BUSINESS_ERROR_00064", 
            "��������J�݂̐ŋ敪�G���[�B");

    /**
     * ��n���̓��t�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00065 = errorMgr.defineErrorInfo(
            65,
            "BUSINESS_ERROR_00065", 
            "�u��������J�݃`�F�b�N�G���[�B�i�����YYYY����n����薢���ɂȂ��Ă���j");

    /**
     * �����������͂̔����敪�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00068 = errorMgr.defineErrorInfo(
            68,
            "BUSINESS_ERROR_00068", 
            "�����������͂̔����敪�G���[�B");

    /**
     * �w�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00069 = errorMgr.defineErrorInfo(
            69,
            "BUSINESS_ERROR_00069", 
            "�����������͒ʒm�L���[�̎w�l�̒l�̃G���[�B");

    /**
     * �������̎w�肪�������͂̎w��ƕs��v�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00070 = errorMgr.defineErrorInfo(
            70,
            "BUSINESS_ERROR_00070", 
            "�������̎w�肪�������͂̎w��ƕs��v�ł��B");

    /**
     * ���������ύX�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00071 = errorMgr.defineErrorInfo(
            71,
            "BUSINESS_ERROR_00071", 
            "�������̔����������������͔��������ƕs��v�ł��B");

    /**
     * �����͋K���Ώۂł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00072 = errorMgr.defineErrorInfo(
            72,
            "BUSINESS_ERROR_00072", 
            "�����͋K���Ώۂł��B");

    /**
     * �w����@@�̒l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00073 = errorMgr.defineErrorInfo(
            73,
            "BUSINESS_ERROR_00073", 
            "�w����@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �������ʂ����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00074 = errorMgr.defineErrorInfo(
            74,
            "BUSINESS_ERROR_00074", 
            "�������ʂ����w��ł��B");

    /**
     * �������ʂ������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00075 = errorMgr.defineErrorInfo(
            75,
            "BUSINESS_ERROR_00075", 
            "�������ʂ������ȊO�̒l�ł��B");

    /**
     * �������ʂ�0�ȉ��̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00076 = errorMgr.defineErrorInfo(
            76,
            "BUSINESS_ERROR_00076", 
            "�������ʂ�0�ȉ��̒l�ł��B");

    /**
     * �������ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00077 = errorMgr.defineErrorInfo(
            77,
            "BUSINESS_ERROR_00077", 
            "�������ʂ̌������s���ł��B");

    /**
     * �m�F�������������͂���Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00078 = errorMgr.defineErrorInfo(
            78,
            "BUSINESS_ERROR_00078", 
            "�m�F�������������͂���Ă��܂���B");

    /**
     * �����R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00079 = errorMgr.defineErrorInfo(
            79,
            "BUSINESS_ERROR_00079", 
            "�����R�[�h�����w��ł��B");

    /**
     * �h�c�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00080 = errorMgr.defineErrorInfo(
            80,
            "BUSINESS_ERROR_00080", 
            "�h�c�����w��ł��B");

    /**
     * �Ɖ�敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00081 = errorMgr.defineErrorInfo(
            81,
            "BUSINESS_ERROR_00081", 
            "�Ɖ�敪�����w��ł��B");

    /**
     * �Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00082 = errorMgr.defineErrorInfo(
            82,
            "BUSINESS_ERROR_00082", 
            "�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �\�[�g�L�[�̃L�[���ڂ����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00085 = errorMgr.defineErrorInfo(
            85,
            "BUSINESS_ERROR_00085", 
            "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");

    /**
     * �L�[���ڂɍ��ږ��ȊO�̒l�����݁B�ǉ�������F[�l1], [�l2], [�l3] ...�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00086 = errorMgr.defineErrorInfo(
            86,
            "BUSINESS_ERROR_00086", 
            "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����^�~�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00087 = errorMgr.defineErrorInfo(
            87,
            "BUSINESS_ERROR_00087", 
            "�����^�~�������w��ł��B");

    /**
     * �����^�~�����hA�F�����h�A�hD�F�~���h�̒l�ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00088 = errorMgr.defineErrorInfo(
            88,
            "BUSINESS_ERROR_00088", 
            "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");

    /**
     * �v���y�[�W�ԍ������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00089 = errorMgr.defineErrorInfo(
            89,
            "BUSINESS_ERROR_00089", 
            "�v���y�[�W�ԍ������w��ł��B");

    /**
     * �v���y�[�W�ԍ��������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00090 = errorMgr.defineErrorInfo(
            90,
            "BUSINESS_ERROR_00090", 
            "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");

    /**
     * �y�[�W���\���s���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00091 = errorMgr.defineErrorInfo(
            91,
            "BUSINESS_ERROR_00091", 
            "�y�[�W���\���s���̓��͂��s���ł��B");

    /**
     * �y�[�W���\���s���������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00092 = errorMgr.defineErrorInfo(
            92,
            "BUSINESS_ERROR_00092", 
            "�y�[�W���\���s���������ȊO�̒l�ł��B");

    /**
     * �w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00093 = errorMgr.defineErrorInfo(
            93,
            "BUSINESS_ERROR_00093", 
            "�w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�ł��B");

    /**
     * �������ʂ̎w�肪����܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00094 = errorMgr.defineErrorInfo(
            94,
            "BUSINESS_ERROR_00094", 
            "�������ʂ̎w�肪����܂���B");

    /**
     * �������ʂ̎w��Ɍ�肪����܂��B�i���l�ȊO�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00095 = errorMgr.defineErrorInfo(
            95,
            "BUSINESS_ERROR_00095", 
            "�������ʂ̎w��Ɍ�肪����܂��B�i���l�ȊO�j");

    /**
     * �������ʂ̎w��Ɍ�肪����܂��B�i0�ȉ��j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00096 = errorMgr.defineErrorInfo(
            96,
            "BUSINESS_ERROR_00096", 
            "�������ʂ̎w��Ɍ�肪����܂��B�i0�ȉ��j");

    /**
     * �������ʂ̎w��Ɍ�肪����܂��B�i�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00097 = errorMgr.defineErrorInfo(
            97,
            "BUSINESS_ERROR_00097", 
            "�������ʂ̎w��Ɍ�肪����܂��B�i�����j");

    /**
     * ��n���@@�̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00099 = errorMgr.defineErrorInfo(
            99,
            "BUSINESS_ERROR_00099", 
            "��n���@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����P���E���s�����̃`�F�b�N�i�s�o���������s�́u�w�l�v�̂ݎw��\�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00114 = errorMgr.defineErrorInfo(
            114,
            "BUSINESS_ERROR_00114", 
            "���s�������g7�F�s�o���������s�h�̏ꍇ�́A�����P���敪���g1�F�w�l�h�ɂȂ�܂���i�s�o���������s�́u�w�l�v�̂ݎw��\�j");

    /**
     * �����P���敪�E�P�� �̐������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00116 = errorMgr.defineErrorInfo(
            116,
            "BUSINESS_ERROR_00116", 
            "�����P���敪���g0�F���s�h�̏ꍇ�́A�����P���w��s�ł��B");

    /**
     * �����̃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00117 = errorMgr.defineErrorInfo(
            117,
            "BUSINESS_ERROR_00117", 
            "���������敪���g1�F��������h�̏ꍇ�́A�����L�������w��s�ł��B");

    /**
     * W�w�l�p�����P���敪�EW�w�l�p�����P�� �̐������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00124 = errorMgr.defineErrorInfo(
            124,
            "BUSINESS_ERROR_00124", 
            "���������敪���g2�FW�w�l�h�A���AW�w�l�p�����P���敪���g0�F���s�h�̏ꍇ�́AW�w�l�p�����P�����w��s�ł��B");

    /**
     * ���������E���s�����̃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00125 = errorMgr.defineErrorInfo(
            125,
            "BUSINESS_ERROR_00125", 
            "���������敪���g2�F�o����܂Œ����h�̏ꍇ�́A���s�����Ɂg1�F�������h��ݒ肵�ĉ������B");

    /**
     * �������������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00126 = errorMgr.defineErrorInfo(
            126,
            "BUSINESS_ERROR_00126", 
            "�������������w��ł��B");

    /**
     * ���s�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00127 = errorMgr.defineErrorInfo(
            127,
            "BUSINESS_ERROR_00127", 
            "���s�����̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ������ɂ�����A�y��������ʒm�L���[�e�[�u���z�́u�������ʃR�[�h�v�̒l�̐������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00128 = errorMgr.defineErrorInfo(
            128,
            "BUSINESS_ERROR_00128", 
            "�������ʃR�[�h�̒l�����݂��Ȃ��R�[�h�l�ł��B�i��������̏ꍇ�j");

    /**
     * �������ɂ�����A�y��������ʒm�L���[�e�[�u���z�́u�������ʃR�[�h�v�̒l�̐������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00129 = errorMgr.defineErrorInfo(
            129,
            "BUSINESS_ERROR_00129", 
            "�������ʃR�[�h�̒l�����݂��Ȃ��R�[�h�l�ł��B�i���������̏ꍇ�j");

    /**
     * �����ʒm�敪�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00130 = errorMgr.defineErrorInfo(
            130,
            "BUSINESS_ERROR_00130", 
            "�����ʒm�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �o����܂Œ����̎戵�\�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00131 = errorMgr.defineErrorInfo(
            131,
            "BUSINESS_ERROR_00131", 
            "�o����܂Œ����̎戵�\���������敪�擾�G���[�B");

    /**
     * ��������J�݃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00136 = errorMgr.defineErrorInfo(
            136,
            "BUSINESS_ERROR_00136", 
            "��������J�݃`�F�b�N�G���[�B�i����G���[�j");

    /**
     * �����ς݂ɂȂ����t�w�l�����̋t�w�l�������������ꍇ�̗�O�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00141 = errorMgr.defineErrorInfo(
            141,
            "BUSINESS_ERROR_00141", 
            "�����ς݂ɂȂ����t�w�l�����̋t�w�l�������C���s�ł��B");

    /**
     * �����������ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00142 = errorMgr.defineErrorInfo(
            142,
            "BUSINESS_ERROR_00142", 
            "�������̒������ʂɂ́A���ϐ��ʂ�葽�����ʂ��w�肵�Ă��������B");

    /**
     * �����������ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00143 = errorMgr.defineErrorInfo(
            143,
            "BUSINESS_ERROR_00143", 
            "�����O���ʂ�葽�����ʂւ̒����͂ł��܂���B");

    /**
     * �������ʂ�������ʂ𒴂���ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00144 = errorMgr.defineErrorInfo(
            144,
            "BUSINESS_ERROR_00144", 
            "�������ʂ�������ʂ𒴂��܂����B");

    /**
     * �����Ԓ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00145 = errorMgr.defineErrorInfo(
            145,
            "BUSINESS_ERROR_00145", 
            "���͂��ꂽ�����͌��݁A�����Ԓ��ƂȂ��Ă���܂���B");

    /**
     * �g�����h�Ɓg����h�����Ɏ�t�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00146 = errorMgr.defineErrorInfo(
            146,
            "BUSINESS_ERROR_00146", 
            "�g�����h�Ɓg����h�����Ɏ�t�s�ł��B");

    /**
     * �戵�\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00147 = errorMgr.defineErrorInfo(
            147,
            "BUSINESS_ERROR_00147", 
            "�w�肳�ꂽ�w���͎戵���\�ł͂���܂���B");

    /**
     * �i�w�l�^���ݒl�j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00148 = errorMgr.defineErrorInfo(
            148,
            "BUSINESS_ERROR_00148", 
            "�w�l�͍��ݒl�̐����{�ł͂���܂���B");

    /**
     * �����^�C�v�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00149 = errorMgr.defineErrorInfo(
            149,
            "BUSINESS_ERROR_00149", 
            "�����^�C�v���s���ł��B");

    /**
     * ���s�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00150 = errorMgr.defineErrorInfo(
            150,
            "BUSINESS_ERROR_00150", 
            "�戵�\�Ȏ��s�����ł͂���܂���B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00151 = errorMgr.defineErrorInfo(
            151,
            "BUSINESS_ERROR_00151", 
            "�戵�\�Ȕ��������ł͂���܂���B");

    /**
     * �������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00152 = errorMgr.defineErrorInfo(
            152,
            "BUSINESS_ERROR_00152", 
            "���������������I������Ă��܂���B");

    /**
     * �o����܂Œ�����~�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00153 = errorMgr.defineErrorInfo(
            153,
            "BUSINESS_ERROR_00153", 
            "���w��̖����͏o����܂Œ�����~�����ł��B");

    /**
     * ���s�K�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00154 = errorMgr.defineErrorInfo(
            154,
            "BUSINESS_ERROR_00154", 
            "���͂��ꂽ�����͌��݁A���s�K�����ƂȂ��Ă���܂��B");

    /**
     * ��������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00155 = errorMgr.defineErrorInfo(
            155,
            "BUSINESS_ERROR_00155", 
            "�Y������������s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00156 = errorMgr.defineErrorInfo(
            156,
            "BUSINESS_ERROR_00156", 
            "�Y�������������s�ł��B");

    /**
     * ����������W�w�l�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00157 = errorMgr.defineErrorInfo(
            157,
            "BUSINESS_ERROR_00157", 
            "����������W�w�l�ł͂���܂���B");

    /**
     * ���X�̎戵�\�s��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00158 = errorMgr.defineErrorInfo(
            158,
            "BUSINESS_ERROR_00158", 
            "���X�̎戵�\�s��ł͂���܂���B");

    /**
     * �����̃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00160 = errorMgr.defineErrorInfo(
            160,
            "BUSINESS_ERROR_00160", 
            "������������������𒴂��Ă��܂��B");

    /**
     * ����\������z�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00161 = errorMgr.defineErrorInfo(
            161,
            "BUSINESS_ERROR_00161", 
            "����\����l�𒴂��Ă��܂��B");

    /**
     * ���s�����ύX�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00165 = errorMgr.defineErrorInfo(
            165,
            "BUSINESS_ERROR_00165", 
            "�o����܂Œ����̏ꍇ�A���s�����͎w��ł��܂���B");

    /**
     * ���s�����ύX�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00166 = errorMgr.defineErrorInfo(
            166,
            "BUSINESS_ERROR_00166", 
            "�������͎��s�������ύX�ł��܂���B");

    /**
     * ���t�\���ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00167 = errorMgr.defineErrorInfo(
            167,
            "BUSINESS_ERROR_00167", 
            "�w�芔���͔��t�\�����𒴂��Ă��܂��B");

    /**
     * �t�w�l��l�̕ύX�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00168 = errorMgr.defineErrorInfo(
            168,
            "BUSINESS_ERROR_00168", 
            "�t�w�l�����́A�s�ꔭ����́A�t�w�l��l������ł��܂���B");

    /**
     * ���N�G�X�g�f�[�^�̌^���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00170 = errorMgr.defineErrorInfo(
            170,
            "BUSINESS_ERROR_00170", 
            "���N�G�X�g�f�[�^�̌^���s���ł��B");

    /**
     * �����R���`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00174 = errorMgr.defineErrorInfo(
            174,
            "BUSINESS_ERROR_00174", 
            "�����R���`�F�b�N�G���[�B");

    /**
     * �ԍό��ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00178 = errorMgr.defineErrorInfo(
            178,
            "BUSINESS_ERROR_00178", 
            "�ԍό��ʂ����w��ł��B");

    /**
     * ���Ϗ����`�F�b�N�i�w��l�ȊO�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00179 = errorMgr.defineErrorInfo(
            179,
            "BUSINESS_ERROR_00179", 
            "���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���Ϗ��ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00180 = errorMgr.defineErrorInfo(
            180,
            "BUSINESS_ERROR_00180", 
            "�����_���w��̏ꍇ�́A���Ϗ��ʂƖ��ׂ̐��ʂ���͂��Ă��������B");

    /**
     * ���Ϗ����`�F�b�N�i�d���̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00182 = errorMgr.defineErrorInfo(
            182,
            "BUSINESS_ERROR_00182", 
            "���Ϗ��ʔԍ����d�����Ă��܂��B");

    /**
     * ���Ϗ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00183 = errorMgr.defineErrorInfo(
            183,
            "BUSINESS_ERROR_00183", 
            "�����_���w��ȊO�́A���Ϗ��ʁA���ׂ̐��ʂɓ��͂ł��܂���B");

    /**
     * �����P���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00184 = errorMgr.defineErrorInfo(
            184,
            "BUSINESS_ERROR_00184", 
            "�����P���敪�����w��ł��B");

    /**
     * �����P���敪�`�F�b�N�i�w��l�ȊO�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00185 = errorMgr.defineErrorInfo(
            185,
            "BUSINESS_ERROR_00185", 
            "�����P���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00187 = errorMgr.defineErrorInfo(
            187,
            "BUSINESS_ERROR_00187", 
            "�����P���敪���g1�F�w�l�h�̏ꍇ�́A�����P�������w��ł��B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00188 = errorMgr.defineErrorInfo(
            188,
            "BUSINESS_ERROR_00188", 
            "�����P���敪���g1�F�w�l�h�̏ꍇ�́A�����P���������ȊO�̒l�ł��B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00189 = errorMgr.defineErrorInfo(
            189,
            "BUSINESS_ERROR_00189", 
            "�����P���敪���g1�F�w�l�h�̏ꍇ�́A�����P����0�ȉ��̒l�ł��B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00190 = errorMgr.defineErrorInfo(
            190,
            "BUSINESS_ERROR_00190", 
            "�����P���敪���g1�F�w�l�h�̏ꍇ�́A�����P���̃T�C�Y���s���ł��B");

    /**
     * �V�K�������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00191 = errorMgr.defineErrorInfo(
            191,
            "BUSINESS_ERROR_00191", 
            "�V�K�������s�B");

    /**
     * �����������Z�q�̕ύX�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00192 = errorMgr.defineErrorInfo(
            192,
            "BUSINESS_ERROR_00192", 
            "�������̔����������Z�q�ƒ������͔����������Z�q����v���Ă��Ȃ��ł��B");

    /**
     * �g���ݓ��������f�[�^�s�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00193 = errorMgr.defineErrorInfo(
            193,
            "BUSINESS_ERROR_00193", 
            "�g���ݓ��������f�[�^�s�����ł��B");

    /**
     * �ݓ�MRF�����t�������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00196 = errorMgr.defineErrorInfo(
            196,
            "BUSINESS_ERROR_00196", 
            "�ݓ�MRF�����t�������s�B");

    /**
     * ���s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00197 = errorMgr.defineErrorInfo(
            197,
            "BUSINESS_ERROR_00197", 
            "���s���������w��ł��B");

    /**
     * ����F���t�A����F���AMMF�ݒ�AMMF�ݒ��񂪎�t�o���Ȃ���Ԃł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00199 = errorMgr.defineErrorInfo(
            199,
            "BUSINESS_ERROR_00199", 
            "����F���t�A����F���AMMF�ݒ�AMMF�ݒ��񂪎�t�o���Ȃ���Ԃł��B");

    /**
     * �������ꗗ���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00200 = errorMgr.defineErrorInfo(
            200,
            "BUSINESS_ERROR_00200", 
            "�������ꗗ���擾�ł��܂���B");

    /**
     * �f�[�^�s�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00201 = errorMgr.defineErrorInfo(
            201,
            "BUSINESS_ERROR_00201", 
            "�ݓ������P�ʂ��f�[�^�s�����ł��B");

    /**
     * �ۗL���Y�Y���f�[�^�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00204 = errorMgr.defineErrorInfo(
            204,
            "BUSINESS_ERROR_00204", 
            "�ۗL���Y�Y���f�[�^�Ȃ��B");

    /**
     * �������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00205 = errorMgr.defineErrorInfo(
            205,
            "BUSINESS_ERROR_00205", 
            "���������ς��܂����B���萔�ł����A������x���͂������Ă��������B");

    /**
     * �m�F���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00206 = errorMgr.defineErrorInfo(
            206,
            "BUSINESS_ERROR_00206", 
            "�m�F���P�������w��ł��B");

    /**
     * ���������敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00208 = errorMgr.defineErrorInfo(
            208,
            "BUSINESS_ERROR_00208", 
            "���������敪�����w��ł��B");

    /**
     * ���������敪�`�F�b�N�i�w��l�ȊO�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00209 = errorMgr.defineErrorInfo(
            209,
            "BUSINESS_ERROR_00209", 
            "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����L�������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00210 = errorMgr.defineErrorInfo(
            210,
            "BUSINESS_ERROR_00210", 
            "�o����܂Œ����̏ꍇ�́A�����L���������w�肵�Ă��������B");

    /**
     * ���������敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00211 = errorMgr.defineErrorInfo(
            211,
            "BUSINESS_ERROR_00211", 
            "���������敪�����w��ł��B");

    /**
     * ���������敪�`�F�b�N�i�w��l�ȊO�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00212 = errorMgr.defineErrorInfo(
            212,
            "BUSINESS_ERROR_00212", 
            "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00213 = errorMgr.defineErrorInfo(
            213,
            "BUSINESS_ERROR_00213", 
            "�v���~�A���^�����Y���i�������͂ł��i�t�w�l�w��j�B");

    /**
     * �v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00214 = errorMgr.defineErrorInfo(
            214,
            "BUSINESS_ERROR_00214", 
            "�v���~�A���^�����Y���i���s���Ȓl�ł��i�t�w�l�w��j�B");

    /**
     * ���������P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00215 = errorMgr.defineErrorInfo(
            215,
            "BUSINESS_ERROR_00215", 
            "���������P���������͂ł��i�t�w�l�w��j�B");

    /**
     * ���������P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00216 = errorMgr.defineErrorInfo(
            216,
            "BUSINESS_ERROR_00216", 
            "���������P�����s���Ȓl�ł��i�t�w�l�w��j�B");

    /**
     * �����������Z�q�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00219 = errorMgr.defineErrorInfo(
            219,
            "BUSINESS_ERROR_00219", 
            "�����������Z�q�������͂ł��i�t�w�l�w��j�B");

    /**
     * �����������Z�q�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00220 = errorMgr.defineErrorInfo(
            220,
            "BUSINESS_ERROR_00220", 
            "�����������Z�q���s���Ȓl�ł��i�t�w�l�w��j�B");

    /**
     * �v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00221 = errorMgr.defineErrorInfo(
            221,
            "BUSINESS_ERROR_00221", 
            "�v���~�A���^�����Y���i�������͂ł��iW�w�l�w��j�B");

    /**
     * �v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00222 = errorMgr.defineErrorInfo(
            222,
            "BUSINESS_ERROR_00222", 
            "�v���~�A���^�����Y���i���s���Ȓl�ł��iW�w�l�w��j�B");

    /**
     * ���������P���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00223 = errorMgr.defineErrorInfo(
            223,
            "BUSINESS_ERROR_00223", 
            "���������P���������͂ł��iW�w�l�w��j�B");

    /**
     * ���������P���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00224 = errorMgr.defineErrorInfo(
            224,
            "BUSINESS_ERROR_00224", 
            "���������P�����s���Ȓl�ł��iW�w�l�w��j�B");

    /**
     * �����������Z�q�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00227 = errorMgr.defineErrorInfo(
            227,
            "BUSINESS_ERROR_00227", 
            "�����������Z�q�������͂ł��iW�w�l�w��j�B");

    /**
     * �����������Z�q�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00228 = errorMgr.defineErrorInfo(
            228,
            "BUSINESS_ERROR_00228", 
            "�����������Z�q���s���Ȓl�ł��iW�w�l�w��j�B");

    /**
     * ���������P���敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00229 = errorMgr.defineErrorInfo(
            229,
            "BUSINESS_ERROR_00229", 
            "���������P���敪�������͂ł��iW�w�l�w��j�B");

    /**
     * ���������P���敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00230 = errorMgr.defineErrorInfo(
            230,
            "BUSINESS_ERROR_00230", 
            "���������P���敪���s���Ȓl�ł��iW�w�l�w��j�B");

    /**
     * �\�[�g�L�[�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00231 = errorMgr.defineErrorInfo(
            231,
            "BUSINESS_ERROR_00231", 
            "�\�[�g�L�[�����w��ł��B");

    /**
     * �\�[�g�L�[�̗v�f�����O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00232 = errorMgr.defineErrorInfo(
            232,
            "BUSINESS_ERROR_00232", 
            "�\�[�g�L�[�̗v�f�����O�ł��B");

    /**
     * ���Ϗ�ԋ敪�̃`�F�b�N�i�w��l�ȊO�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00233 = errorMgr.defineErrorInfo(
            233,
            "BUSINESS_ERROR_00233", 
            "���Ϗ�ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �V�K�����s�ꃁ�b�Z�[�W���M���s�ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00237 = errorMgr.defineErrorInfo(
            237,
            "BUSINESS_ERROR_00237", 
            "�V�K�����s�ꃁ�b�Z�[�W���M���s�B");

    /**
     * �ݓ�MRF������t���s�ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00238 = errorMgr.defineErrorInfo(
            238,
            "BUSINESS_ERROR_00238", 
            "�ݓ�MRF������t���s�B");

    /**
     * �ݓ�������t���s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00239 = errorMgr.defineErrorInfo(
            239,
            "BUSINESS_ERROR_00239", 
            "�ݓ�������t���s�B");

    /**
     * �ݓ�����������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00240 = errorMgr.defineErrorInfo(
            240,
            "BUSINESS_ERROR_00240", 
            "�ݓ�����������s�B");

    /**
     * ���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00245 = errorMgr.defineErrorInfo(
            245,
            "BUSINESS_ERROR_00245", 
            "���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B");

    /**
     * ���Ϗ��ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00246 = errorMgr.defineErrorInfo(
            246,
            "BUSINESS_ERROR_00246", 
            "���Ϗ��ʂ�0�����̒l�ł��B");

    /**
     * �ݐϓ����̎w����@@�����v���Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00248 = errorMgr.defineErrorInfo(
            248,
            "BUSINESS_ERROR_00248", 
            "�ݐϓ����̎w����@@�����v���Ă��܂���B");

    /**
     * �ݐϓ�����������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00249 = errorMgr.defineErrorInfo(
            249,
            "BUSINESS_ERROR_00249", 
            "�ݐϓ�����������`�F�b�N�G���[�B");

    /**
     * �g���ݓ�����������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00250 = errorMgr.defineErrorInfo(
            250,
            "BUSINESS_ERROR_00250", 
            "�g���ݓ�����������擾�ł��܂���B");

    /**
     * ����K���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00251 = errorMgr.defineErrorInfo(
            251,
            "BUSINESS_ERROR_00251", 
            "�g���ݓ��������������K�����ł��B");

    /**
     * ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00252 = errorMgr.defineErrorInfo(
            252,
            "BUSINESS_ERROR_00252", 
            "��񓖂���̏���������z�G���[�B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00253 = errorMgr.defineErrorInfo(
            253,
            "BUSINESS_ERROR_00253", 
            "��񓖂���̉����������z�G���[�B");

    /**
     * ���꒍�����̔��������ɒ�������Ă��Ȃ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00254 = errorMgr.defineErrorInfo(
            254,
            "BUSINESS_ERROR_00254", 
            "���꒍�����̔��������ɒ�������Ă��܂��B");

    /**
     * ���t���z�����t�\�z�𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00255 = errorMgr.defineErrorInfo(
            255,
            "BUSINESS_ERROR_00255", 
            "���t���z�����t�\�z�𒴂��Ă��܂��B");

    /**
     * �ݐϓ����������J�݂Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00256 = errorMgr.defineErrorInfo(
            256,
            "BUSINESS_ERROR_00256", 
            "�ݐϓ����������J�݂Ȃ��B");

    /**
     * ���\�c���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00258 = errorMgr.defineErrorInfo(
            258,
            "BUSINESS_ERROR_00258", 
            "�������ʂ����\�c���𒴂��Ă��܂��B");

    /**
     * �ݓ������P��.�������ƌ��݂̔����������������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00259 = errorMgr.defineErrorInfo(
            259,
            "BUSINESS_ERROR_00259", 
            "�ݓ������P�ʂ̔������ƌ��݂̔���������v���Ă��Ȃ��ł��B");

    /**
     * �ݓ������������ρi�V�K�����j�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00260 = errorMgr.defineErrorInfo(
            260,
            "BUSINESS_ERROR_00260", 
            "�ݓ������������ρi�V�K�����j�ł͂���܂���B");

    /**
     * �w�肳�ꂽ���������Ɏ�������Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00261 = errorMgr.defineErrorInfo(
            261,
            "BUSINESS_ERROR_00261", 
            "�w�肳�ꂽ���������Ɏ�������Ă��܂��B");

    /**
     * ��񒍕��σG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00262 = errorMgr.defineErrorInfo(
            262,
            "BUSINESS_ERROR_00262", 
            "��񒍕��σG���[�B");

    /**
     * ���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00263 = errorMgr.defineErrorInfo(
            263,
            "BUSINESS_ERROR_00263", 
            "���敪�����w��ł��B");

    /**
     * ���敪�`�F�b�N�i�w��l�ȊO�ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00264 = errorMgr.defineErrorInfo(
            264,
            "BUSINESS_ERROR_00264", 
            "���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ����s��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00265 = errorMgr.defineErrorInfo(
            265,
            "BUSINESS_ERROR_00265", 
            "����s�ꂪ���w��ł��B");

    /**
     * �w����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00266 = errorMgr.defineErrorInfo(
            266,
            "BUSINESS_ERROR_00266", 
            "�w����ʂ����w��ł��B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00267 = errorMgr.defineErrorInfo(
            267,
            "BUSINESS_ERROR_00267", 
            "���������w��ł��B");

    /**
     * �����`�F�b�N�i���t�`���G���[�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00268 = errorMgr.defineErrorInfo(
            268,
            "BUSINESS_ERROR_00268", 
            "�������x�x�x�x�l�l�`���œ��͂��Ă��������B");

    /**
     * �I�v�V�������i�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00269 = errorMgr.defineErrorInfo(
            269,
            "BUSINESS_ERROR_00269", 
            "�I�v�V�������i�敪�����w��ł��B");

    /**
     * �I�v�V�������i�敪�`�F�b�N�i�w��l�ȊO�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00270 = errorMgr.defineErrorInfo(
            270,
            "BUSINESS_ERROR_00270", 
            "�I�v�V�������i�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �s�g���i�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00271 = errorMgr.defineErrorInfo(
            271,
            "BUSINESS_ERROR_00271", 
            "�s�g���i�����w��ł��B");

    /**
     * �s�g���i�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00272 = errorMgr.defineErrorInfo(
            272,
            "BUSINESS_ERROR_00272", 
            "�s�g���i�������ȊO�̒l�ł��B");

    /**
     * �s�g���i�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00273 = errorMgr.defineErrorInfo(
            273,
            "BUSINESS_ERROR_00273", 
            "�s�g���i��0�ȉ��̒l�ł��B");

    /**
     * �s�g���i�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00274 = errorMgr.defineErrorInfo(
            274,
            "BUSINESS_ERROR_00274", 
            "�s�g���i�̃T�C�Y���s���ł��B");

    /**
     * �����~�ڋq�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00275 = errorMgr.defineErrorInfo(
            275,
            "BUSINESS_ERROR_00275", 
            "�����~�ڋq�G���[�B");

    /**
     * �Ǘ����b�N�ڋq�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00276 = errorMgr.defineErrorInfo(
            276,
            "BUSINESS_ERROR_00276", 
            "�Ǘ����b�N�ڋq�G���[�B");

    /**
     * ID�̗v�f���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00282 = errorMgr.defineErrorInfo(
            282,
            "BUSINESS_ERROR_00282", 
            "ID�̗v�f�����O�ł��B");

    /**
     * �����J�݃`�F�b�N�i�h�I�v�V�����h�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00283 = errorMgr.defineErrorInfo(
            283,
            "BUSINESS_ERROR_00283", 
            "���q�l�̃I�v�V�����������J�݂���Ă���܂���B");

    /**
     * �����J�݃`�F�b�N�i�h�敨�h�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00284 = errorMgr.defineErrorInfo(
            284,
            "BUSINESS_ERROR_00284", 
            "���q�l�̐敨�������J�݂���Ă���܂���B");

    /**
     * �ԍό��ʗv�f���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00285 = errorMgr.defineErrorInfo(
            285,
            "BUSINESS_ERROR_00285", 
            "���ׂĂ̕ԍό��ʂ̐��ʂ����w��ł��B");

    /**
     * ���o�^�������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00286 = errorMgr.defineErrorInfo(
            286,
            "BUSINESS_ERROR_00286", 
            "���o�^�������s�B");

    /**
     * �o����܂Œ����戵�\�̔���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00289 = errorMgr.defineErrorInfo(
            289,
            "BUSINESS_ERROR_00289", 
            "�o����܂Œ����ł͂Ȃ��ꍇ�A�������蒍���ł��B");

    /**
     * ����������������J�݂̔���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00292 = errorMgr.defineErrorInfo(
            292,
            "BUSINESS_ERROR_00292", 
            "���q�l�͌�����������������J�݂���Ă���܂���B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00293 = errorMgr.defineErrorInfo(
            293,
            "BUSINESS_ERROR_00293", 
            "�����P���`�F�b�N�G���[�i�w�l���K�؂ł͂���܂���j�B");

    /**
     * �w������Ɉ�v���钍���̔���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00295 = errorMgr.defineErrorInfo(
            295,
            "BUSINESS_ERROR_00295", 
            "SONAR����ʒm���ꂽ���ʃR�[�h�ɊY�����钍�����������A�s���ɑ��݂��܂��B");

    /**
     * �������ʂ��ԍω\�c�����ʂ𒴂��̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00299 = errorMgr.defineErrorInfo(
            299,
            "BUSINESS_ERROR_00299", 
            "�������ʂ��ԍω\�c�����ʂ𒴂��Ă��܂��B");

    /**
     * ��萔�ʂ�蒍�����ʂ������ł��邪�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00300 = errorMgr.defineErrorInfo(
            300,
            "BUSINESS_ERROR_00300", 
            "��萔�ʂ��������ʂ𒴂��Ă��܂��B");

    /**
     * �����R�[�h�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00301 = errorMgr.defineErrorInfo(
            301,
            "BUSINESS_ERROR_00301", 
            "�w�肵�������R�[�h�ɍ��v���Ă�����������݂��܂���B");

    /**
     * �����������戵�ΏۊO�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00302 = errorMgr.defineErrorInfo(
            302,
            "BUSINESS_ERROR_00302", 
            "�����������戵�ΏۊO�ł��B");

    /**
     * �ԍω\���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00303 = errorMgr.defineErrorInfo(
            303,
            "BUSINESS_ERROR_00303", 
            "�������ʂ��ԍω\���ʂ𒴂��Ă��܂��B");

    /**
     * �ԍω\�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00304 = errorMgr.defineErrorInfo(
            304,
            "BUSINESS_ERROR_00304", 
            "�ԍω\�����I�[�o�[�B");

    /**
     * �ݓ������t�������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00305 = errorMgr.defineErrorInfo(
            305,
            "BUSINESS_ERROR_00305", 
            "�ݓ������t�������s�B");

    /**
     * ���\�Ώۖ��ׂȂ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00307 = errorMgr.defineErrorInfo(
            307,
            "BUSINESS_ERROR_00307", 
            "���\�Ώۖ��ׂȂ��B");

    /**
     * �v�w�l�����P���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00313 = errorMgr.defineErrorInfo(
            313,
            "BUSINESS_ERROR_00313", 
            "W�w�l�����P���������͂ł��iW�w�l�w��j�B");

    /**
     * �v�w�l�����P���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00314 = errorMgr.defineErrorInfo(
            314,
            "BUSINESS_ERROR_00314", 
            "W�w�l�����P�����s���Ȓl�ł��iW�w�l�w��j�B");

    /**
     * �����R�[�h�A���A���敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00320 = errorMgr.defineErrorInfo(
            320,
            "BUSINESS_ERROR_00320", 
            "�����R�[�h�A���A���敪�����w��ł��B");

    /**
     * ���Ϗ����`�F�b�N�i�����ȊO�̒l�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00329 = errorMgr.defineErrorInfo(
            329,
            "BUSINESS_ERROR_00329", 
            "���Ϗ��ʂ������ȊO�̒l�ł��B");

    /**
     * �����w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00334 = errorMgr.defineErrorInfo(
            334,
            "BUSINESS_ERROR_00334", 
            "�����w��G���[�B");

    /**
     * ���t�J�n���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00335 = errorMgr.defineErrorInfo(
            335,
            "BUSINESS_ERROR_00335", 
            "���t�J�n���G���[�B");

    /**
     * ���t�I�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00336 = errorMgr.defineErrorInfo(
            336,
            "BUSINESS_ERROR_00336", 
            "���t�I�����G���[�B");

    /**
     * ���^�抷�J�n���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00337 = errorMgr.defineErrorInfo(
            337,
            "BUSINESS_ERROR_00337", 
            "���^�抷�J�n���G���[�B");

    /**
     * ���^�抷�I�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00338 = errorMgr.defineErrorInfo(
            338,
            "BUSINESS_ERROR_00338", 
            "���^�抷�I�����G���[�B");

    /**
     * ���搿���J�n���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00339 = errorMgr.defineErrorInfo(
            339,
            "BUSINESS_ERROR_00339", 
            "���搿���J�n���G���[�B");

    /**
     * ���搿���I�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00340 = errorMgr.defineErrorInfo(
            340,
            "BUSINESS_ERROR_00340", 
            "���搿���I�����G���[�B");

    /**
     * �Œ�����i�V�K���t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00341 = errorMgr.defineErrorInfo(
            341,
            "BUSINESS_ERROR_00341", 
            "�Œ�����i�V�K���t�j�G���[�B");

    /**
     * �P�ʌ����i�V�K���t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00342 = errorMgr.defineErrorInfo(
            342,
            "BUSINESS_ERROR_00342", 
            "�P�ʌ����i�V�K���t�j�G���[�B");

    /**
     * �Œ���z�i�V�K���t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00343 = errorMgr.defineErrorInfo(
            343,
            "BUSINESS_ERROR_00343", 
            "�Œ���z�i�V�K���t�j�G���[�B");

    /**
     * �P�ʋ��z�i�V�K���t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00344 = errorMgr.defineErrorInfo(
            344,
            "BUSINESS_ERROR_00344", 
            "�P�ʋ��z�i�V�K���t�j�G���[�B");

    /**
     * �Œ�����i�ǉ����t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00345 = errorMgr.defineErrorInfo(
            345,
            "BUSINESS_ERROR_00345", 
            "�Œ�����i�ǉ����t�j�G���[�B");

    /**
     * �P�ʌ����i�ǉ����t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00346 = errorMgr.defineErrorInfo(
            346,
            "BUSINESS_ERROR_00346", 
            "�P�ʌ����i�ǉ����t�j�G���[�B");

    /**
     * �Œ���z�i�ǉ����t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00347 = errorMgr.defineErrorInfo(
            347,
            "BUSINESS_ERROR_00347", 
            "�Œ���z�i�ǉ����t�j�G���[�B");

    /**
     * �P�ʋ��z�i�ǉ����t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00348 = errorMgr.defineErrorInfo(
            348,
            "BUSINESS_ERROR_00348", 
            "�P�ʋ��z�i�ǉ����t�j�G���[�B");

    /**
     * �Œ�����i���j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00349 = errorMgr.defineErrorInfo(
            349,
            "BUSINESS_ERROR_00349", 
            "�Œ�����i���j�G���[�B");

    /**
     * �P�ʌ����i���j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00350 = errorMgr.defineErrorInfo(
            350,
            "BUSINESS_ERROR_00350", 
            "�P�ʌ����i���j�G���[�B");

    /**
     * �Œ���z�i���j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00351 = errorMgr.defineErrorInfo(
            351,
            "BUSINESS_ERROR_00351", 
            "�Œ���z�i���j�G���[�B");

    /**
     * �P�ʋ��z�i���j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00352 = errorMgr.defineErrorInfo(
            352,
            "BUSINESS_ERROR_00352", 
            "�P�ʋ��z�i���j�G���[�B");

    /**
     * �Œ�����i�抷�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00353 = errorMgr.defineErrorInfo(
            353,
            "BUSINESS_ERROR_00353", 
            "�Œ�����i�抷�j�G���[�B");

    /**
     * �P�ʌ����i�抷�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00354 = errorMgr.defineErrorInfo(
            354,
            "BUSINESS_ERROR_00354", 
            "�P�ʌ����i�抷�j�G���[�B");

    /**
     * �Œ���z�i�抷�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00355 = errorMgr.defineErrorInfo(
            355,
            "BUSINESS_ERROR_00355", 
            "�Œ���z�i�抷�j�G���[�B");

    /**
     * �P�ʋ��z�i�抷�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00356 = errorMgr.defineErrorInfo(
            356,
            "BUSINESS_ERROR_00356", 
            "�P�ʋ��z�i�抷�j�G���[�B");

    /**
     * ���t�Œᐔ�ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00360 = errorMgr.defineErrorInfo(
            360,
            "BUSINESS_ERROR_00360", 
            "���t�Œᐔ�ʃG���[�B");

    /**
     * ���t�P�ʐ��ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00361 = errorMgr.defineErrorInfo(
            361,
            "BUSINESS_ERROR_00361", 
            "���t�P�ʐ��ʃG���[�B");

    /**
     * �戵�s�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00362 = errorMgr.defineErrorInfo(
            362,
            "BUSINESS_ERROR_00362", 
            "�戵�s�����G���[�B");

    /**
     * ����s�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00363 = errorMgr.defineErrorInfo(
            363,
            "BUSINESS_ERROR_00363", 
            "����s�����G���[�B");

    /**
     * �U�����s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00366 = errorMgr.defineErrorInfo(
            366,
            "BUSINESS_ERROR_00366", 
            "�U�����s�����`�F�b�N�G���[�B");

    /**
     * ���z�w���񂠂�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00367 = errorMgr.defineErrorInfo(
            367,
            "BUSINESS_ERROR_00367", 
            "���z�w���񂠂�G���[�B");

    /**
     * ���Œᐔ�ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00368 = errorMgr.defineErrorInfo(
            368,
            "BUSINESS_ERROR_00368", 
            "���Œᐔ�ʃG���[�B");

    /**
     * ���P�ʐ��ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00369 = errorMgr.defineErrorInfo(
            369,
            "BUSINESS_ERROR_00369", 
            "���P�ʐ��ʃG���[�B");

    /**
     * �抷�Œᐔ�ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00370 = errorMgr.defineErrorInfo(
            370,
            "BUSINESS_ERROR_00370", 
            "�抷�Œᐔ�ʃG���[�B");

    /**
     * �抷�P�ʐ��ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00371 = errorMgr.defineErrorInfo(
            371,
            "BUSINESS_ERROR_00371", 
            "�抷�P�ʐ��ʃG���[�B");

    /**
     * ����������o�^����Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00372 = errorMgr.defineErrorInfo(
            372,
            "BUSINESS_ERROR_00372", 
            "����������o�^����Ă��܂���B");

    /**
     * ���t�\�����ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00373 = errorMgr.defineErrorInfo(
            373,
            "BUSINESS_ERROR_00373", 
            "���t�\�����ł͂���܂���B");

    /**
     * ���\�����ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00374 = errorMgr.defineErrorInfo(
            374,
            "BUSINESS_ERROR_00374", 
            "���\�����ł͂���܂���B");

    /**
     * �抷�\�����ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00375 = errorMgr.defineErrorInfo(
            375,
            "BUSINESS_ERROR_00375", 
            "�抷�\�����ł͂���܂���B");

    /**
     * ���Y�ڋq�����݋��z�w��̉����s���Ă��邩���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00376 = errorMgr.defineErrorInfo(
            376,
            "BUSINESS_ERROR_00376", 
            "���Y�ڋq�����݋��z�w��̉����s���Ă��܂��B");

    /**
     * �������o�^����Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00377 = errorMgr.defineErrorInfo(
            377,
            "BUSINESS_ERROR_00377", 
            "�������o�^����Ă��܂���B");

    /**
     * ���Y����̔������ƌ��݂̔���������v���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00378 = errorMgr.defineErrorInfo(
            378,
            "BUSINESS_ERROR_00378", 
            "���Y����̔������ƌ��݂̔���������v���܂���B");

    /**
     * ���Y����͖�蒆�����ςȂ̂ŁA����\�ȏ�Ԃł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00379 = errorMgr.defineErrorInfo(
            379,
            "BUSINESS_ERROR_00379", 
            "���Y����͖�蒆�����ςȂ̂ŁA����\�ȏ�Ԃł͂���܂���B");

    /**
     * ���Y����͔�������Ă��Ȃ����߁A����\�ȏ�Ԃł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00380 = errorMgr.defineErrorInfo(
            380,
            "BUSINESS_ERROR_00380", 
            "���Y����͔�������Ă��Ȃ����߁A����\�ȏ�Ԃł͂���܂���B");

    /**
     * �����Ԃ��L���łȂ����߁A����\�ȏ�Ԃł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00382 = errorMgr.defineErrorInfo(
            382,
            "BUSINESS_ERROR_00382", 
            "�����Ԃ��L���łȂ����߁A����\�ȏ�Ԃł͂���܂���B");

    /**
     * �\���\�Ȗ������o�^����Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00383 = errorMgr.defineErrorInfo(
            383,
            "BUSINESS_ERROR_00383", 
            "�\���\�Ȗ������o�^����Ă��܂���B");

    /**
     * �ۗL�c���������߃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00387 = errorMgr.defineErrorInfo(
            387,
            "BUSINESS_ERROR_00387", 
            "�ۗL�c���������߃G���[�B");

    /**
     * ���Y�����͎����~���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00389 = errorMgr.defineErrorInfo(
            389,
            "BUSINESS_ERROR_00389", 
            "���Y�����͎����~���ł��B");

    /**
     * �c�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00390 = errorMgr.defineErrorInfo(
            390,
            "BUSINESS_ERROR_00390", 
            "�c��������܂���B");

    /**
     * �Y�����������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00391 = errorMgr.defineErrorInfo(
            391,
            "BUSINESS_ERROR_00391", 
            "�Y��������������݂��܂���B");

    /**
     * ���M���抷�����ꗗ�s�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00392 = errorMgr.defineErrorInfo(
            392,
            "BUSINESS_ERROR_00392", 
            "���M���抷�����ꗗ�s���擾�ł��܂���B");

    /**
     * �ŋ敪���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00393 = errorMgr.defineErrorInfo(
            393,
            "BUSINESS_ERROR_00393", 
            "�ŋ敪���s���ł��B");

    /**
     * ����������s�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00394 = errorMgr.defineErrorInfo(
            394,
            "BUSINESS_ERROR_00394", 
            "����������s�B");

    /**
     * ������t�������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00395 = errorMgr.defineErrorInfo(
            395,
            "BUSINESS_ERROR_00395", 
            "������t�������s�B");

    /**
     * ���t�����ꗗ���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00397 = errorMgr.defineErrorInfo(
            397,
            "BUSINESS_ERROR_00397", 
            "���t�����ꗗ���擾�ł��܂���B");

    /**
     * �Y������f�[�^�����݂��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00398 = errorMgr.defineErrorInfo(
            398,
            "BUSINESS_ERROR_00398", 
            "�Y������f�[�^�����݂��܂���B");

    /**
     * �w����@@�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00400 = errorMgr.defineErrorInfo(
            400,
            "BUSINESS_ERROR_00400", 
            "�w����@@�����w��ł��B");

    /**
     * �������@@�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00401 = errorMgr.defineErrorInfo(
            401,
            "BUSINESS_ERROR_00401", 
            "�������@@�����w��ł��B");

    /**
     * �������@@�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00402 = errorMgr.defineErrorInfo(
            402,
            "BUSINESS_ERROR_00402", 
            "�������@@�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���ϕ��@@�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00403 = errorMgr.defineErrorInfo(
            403,
            "BUSINESS_ERROR_00403", 
            "���ϕ��@@�����w��ł��B");

    /**
     * ���ϕ��@@�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00404 = errorMgr.defineErrorInfo(
            404,
            "BUSINESS_ERROR_00404", 
            "���ϕ��@@�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ��n���@@�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00405 = errorMgr.defineErrorInfo(
            405,
            "BUSINESS_ERROR_00405", 
            "��n���@@�����w��ł��B");

    /**
     * �����������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00406 = errorMgr.defineErrorInfo(
            406,
            "BUSINESS_ERROR_00406", 
            "�����������w��ł��B");

    /**
     * �o�^�X�V��񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00411 = errorMgr.defineErrorInfo(
            411,
            "BUSINESS_ERROR_00411", 
            "�o�^�X�V��񂪖��w��ł��B");

    /**
     * �o�^�X�V���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00412 = errorMgr.defineErrorInfo(
            412,
            "BUSINESS_ERROR_00412", 
            "�o�^�X�V���̗v�f�����O�ł��B");

    /**
     * �o����܂Œ����戵�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00413 = errorMgr.defineErrorInfo(
            413,
            "BUSINESS_ERROR_00413", 
            "�o����܂Œ����戵�s�B");

    /**
     * �����R�[�h�i�抷��j�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00414 = errorMgr.defineErrorInfo(
            414,
            "BUSINESS_ERROR_00414", 
            "�����R�[�h�i�抷��j�����w��ł��B");

    /**
     * ���t�����敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00415 = errorMgr.defineErrorInfo(
            415,
            "BUSINESS_ERROR_00415", 
            "���t�����敪�����w��ł��B");

    /**
     * ���t�����敪���w��l�ȊO�̏ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00416 = errorMgr.defineErrorInfo(
            416,
            "BUSINESS_ERROR_00416", 
            "���t�����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �i���t�j���ʂ����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00418 = errorMgr.defineErrorInfo(
            418,
            "BUSINESS_ERROR_00418", 
            "�i���t�j���ʂ����w��ł��B");

    /**
     * (���t)���ʃ`�F�b�N�i���ʂ����l�ȊO�̏ꍇ�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00419 = errorMgr.defineErrorInfo(
            419,
            "BUSINESS_ERROR_00419", 
            "�i���t�j���ʂ����l�ȊO�̒l�ł��B");

    /**
     * (���t)���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00420 = errorMgr.defineErrorInfo(
            420,
            "BUSINESS_ERROR_00420", 
            "�i���t�j���ʂ�0�ȉ��̒l�ł��B");

    /**
     * (���t)���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00421 = errorMgr.defineErrorInfo(
            421,
            "BUSINESS_ERROR_00421", 
            "�i���t�j���ʂ̃T�C�Y���s���ł��B");

    /**
     * �����N�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00429 = errorMgr.defineErrorInfo(
            429,
            "BUSINESS_ERROR_00429", 
            "�����N�������w��ł��B");

    /**
     * �����N���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00430 = errorMgr.defineErrorInfo(
            430,
            "BUSINESS_ERROR_00430", 
            "�����N�������l�ȊO�̒l�ł��B");

    /**
     * �����N���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00431 = errorMgr.defineErrorInfo(
            431,
            "BUSINESS_ERROR_00431", 
            "�����N���̃T�C�Y���s���ł��B");

    /**
     * �����N���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00432 = errorMgr.defineErrorInfo(
            432,
            "BUSINESS_ERROR_00432", 
            "�����N���̌���01����12�܂ł̐��l�ȊO�ł��B");

    /**
     * �c�Ɠ�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00433 = errorMgr.defineErrorInfo(
            433,
            "BUSINESS_ERROR_00433", 
            "�c�Ɠ���������l�ȊO�̒l�ł��B");

    /**
     * IPO�o�^�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00434 = errorMgr.defineErrorInfo(
            434,
            "BUSINESS_ERROR_00434", 
            "IPO�o�^�敪�����w��ł��B");

    /**
     * IPO�o�^�敪�ڍׂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00435 = errorMgr.defineErrorInfo(
            435,
            "BUSINESS_ERROR_00435", 
            "IPO�o�^�敪�ڍׂ����w��ł��B");

    /**
     * IPO�o�^�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00436 = errorMgr.defineErrorInfo(
            436,
            "BUSINESS_ERROR_00436", 
            "IPO�o�^�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * IPO�o�^�敪�ڍׂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00437 = errorMgr.defineErrorInfo(
            437,
            "BUSINESS_ERROR_00437", 
            "IPO�o�^�敪�ڍׂ����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00439 = errorMgr.defineErrorInfo(
            439,
            "BUSINESS_ERROR_00439", 
            "�����R�[�h�̃T�C�Y���s���ł��B");

    /**
     * �������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00440 = errorMgr.defineErrorInfo(
            440,
            "BUSINESS_ERROR_00440", 
            "�V�K�������A�������͕K�{���͍��ڂł��B");

    /**
     * �������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00441 = errorMgr.defineErrorInfo(
            441,
            "BUSINESS_ERROR_00441", 
            "�������̃T�C�Y���s���ł��B");

    /**
     * ���J���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00442 = errorMgr.defineErrorInfo(
            442,
            "BUSINESS_ERROR_00442", 
            "�X�P�W���[������ς̏ꍇ�A���J���͕K�{���͍��ڂł��B");

    /**
     * �s��R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00443 = errorMgr.defineErrorInfo(
            443,
            "BUSINESS_ERROR_00443", 
            "�s��R�[�h�����w��ł��B");

    /**
     * �������敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00444 = errorMgr.defineErrorInfo(
            444,
            "BUSINESS_ERROR_00444", 
            "�������敪�����w��ł��B");

    /**
     * �������敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00445 = errorMgr.defineErrorInfo(
            445,
            "BUSINESS_ERROR_00445", 
            "�������敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00446 = errorMgr.defineErrorInfo(
            446,
            "BUSINESS_ERROR_00446", 
            "�����������l�����l�ł͂���܂���B");

    /**
     * �����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00447 = errorMgr.defineErrorInfo(
            447,
            "BUSINESS_ERROR_00447", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A�����������l�̃T�C�Y���s���ł��B");

    /**
     * �����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00448 = errorMgr.defineErrorInfo(
            448,
            "BUSINESS_ERROR_00448", 
            "�������敪���g�f�B�X�J�E���g���i���j�h�̏ꍇ�́A�����������l�̃T�C�Y��������2���^������2���ȓ��̃����W�ł͂���܂���B");

    /**
     * ����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00449 = errorMgr.defineErrorInfo(
            449,
            "BUSINESS_ERROR_00449", 
            "����������l�����l�ł͂���܂���B");

    /**
     * ����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00450 = errorMgr.defineErrorInfo(
            450,
            "BUSINESS_ERROR_00450", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A����������l�̃T�C�Y���s���ł��B");

    /**
     * ����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00451 = errorMgr.defineErrorInfo(
            451,
            "BUSINESS_ERROR_00451", 
            "�������敪���g�f�B�X�J�E���g���i���j�h�̏ꍇ�́A����������l�̃T�C�Y��������2���^������2���ȓ��̃����W�ł͂���܂���B");

    /**
     * �������񎦓��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00452 = errorMgr.defineErrorInfo(
            452,
            "BUSINESS_ERROR_00452", 
            "�������񎦓������w��ł��B");

    /**
     * ���吔�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00453 = errorMgr.defineErrorInfo(
            453,
            "BUSINESS_ERROR_00453", 
            "���吔�ʂ����l�ȊO�̒l�ł��B");

    /**
     * ���吔�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00454 = errorMgr.defineErrorInfo(
            454,
            "BUSINESS_ERROR_00454", 
            "���吔�ʂ̃T�C�Y���s���ł��B");

    /**
     * ���o���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00455 = errorMgr.defineErrorInfo(
            455,
            "BUSINESS_ERROR_00455", 
            "���o���ʂ����l�ȊO�̒l�ł��B");

    /**
     * ���o���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00456 = errorMgr.defineErrorInfo(
            456,
            "BUSINESS_ERROR_00456", 
            "���o���ʂ̃T�C�Y���s���ł��B");

    /**
     * ���Ў戵���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00457 = errorMgr.defineErrorInfo(
            457,
            "BUSINESS_ERROR_00457", 
            "���Ў戵���ʂ����l�ȊO�̒l�ł��B");

    /**
     * ���Ў戵���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00458 = errorMgr.defineErrorInfo(
            458,
            "BUSINESS_ERROR_00458", 
            "���Ў戵���ʂ̃T�C�Y���s���ł��B");

    /**
     * �w���\���P�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00459 = errorMgr.defineErrorInfo(
            459,
            "BUSINESS_ERROR_00459", 
            "�w���\���P�ʂ����l�ȊO�̒l�ł��B");

    /**
     * �w���\���P�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00460 = errorMgr.defineErrorInfo(
            460,
            "BUSINESS_ERROR_00460", 
            "�w���\���P�ʂ̃T�C�Y���s���ł��B");

    /**
     * ���Ў戵���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00461 = errorMgr.defineErrorInfo(
            461,
            "BUSINESS_ERROR_00461", 
            "���Ў戵���ʂ́A�w���\���P�ʂ��傫���l����͂��Ă��������B");

    /**
     * ���Ў戵���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00462 = errorMgr.defineErrorInfo(
            462,
            "BUSINESS_ERROR_00462", 
            "���Ў戵���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");

    /**
     * ���o���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00463 = errorMgr.defineErrorInfo(
            463,
            "BUSINESS_ERROR_00463", 
            "���o���ʂ́A�w���\���P�ʂ̐����{�Ŏw�肵�Ă��������B");

    /**
     * ���吔�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00464 = errorMgr.defineErrorInfo(
            464,
            "BUSINESS_ERROR_00464", 
            "���吔�ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");

    /**
     * ���݂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00465 = errorMgr.defineErrorInfo(
            465,
            "BUSINESS_ERROR_00465", 
            "���݂����l�ȊO�̒l�ł��B");

    /**
     * ���݂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00466 = errorMgr.defineErrorInfo(
            466,
            "BUSINESS_ERROR_00466", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A���݂̃T�C�Y���s���ł��B");

    /**
     * ���݂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00467 = errorMgr.defineErrorInfo(
            467,
            "BUSINESS_ERROR_00467", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A���݂������l�ł͂���܂���B");

    /**
     * ���݂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00468 = errorMgr.defineErrorInfo(
            468,
            "BUSINESS_ERROR_00468", 
            "�������敪���g�f�B�X�J�E���g���i���j�h�̏ꍇ�́A���݂̃T�C�Y��������2���^������2���ȓ��͈̔͊O�ł��B");

    /**
     * �\���p�P�ʋ敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00469 = errorMgr.defineErrorInfo(
            469,
            "BUSINESS_ERROR_00469", 
            "�\���p�P�ʋ敪�����w��ł��B");

    /**
     * �\���p�P�ʋ敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00470 = errorMgr.defineErrorInfo(
            470,
            "BUSINESS_ERROR_00470", 
            "�\���p�P�ʋ敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �劲���،���Ж��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00471 = errorMgr.defineErrorInfo(
            471,
            "BUSINESS_ERROR_00471", 
            "�劲���،���Ж������w��ł��B");

    /**
     * �劲���،���Ж��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00472 = errorMgr.defineErrorInfo(
            472,
            "BUSINESS_ERROR_00472", 
            "�劲���،���Ж��̃T�C�Y���s���ł��B");

    /**
     * �u�b�N�r���f�B���O�J�n�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00473 = errorMgr.defineErrorInfo(
            473,
            "BUSINESS_ERROR_00473", 
            "�u�b�N�r���f�B���O�J�n���������w��ł��B");

    /**
     * �u�b�N�r���f�B���O�I�������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00474 = errorMgr.defineErrorInfo(
            474,
            "BUSINESS_ERROR_00474", 
            "�u�b�N�r���f�B���O�I�����������w��ł��B");

    /**
     * ���J���i������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00475 = errorMgr.defineErrorInfo(
            475,
            "BUSINESS_ERROR_00475", 
            "���茈��敪���g����h�̏ꍇ�́A���J���i������̊J�n���������w��ł��B");

    /**
     * ���J���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00476 = errorMgr.defineErrorInfo(
            476,
            "BUSINESS_ERROR_00476", 
            "���J���i�����l�ȊO�̒l�ł��B");

    /**
     * ���J���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00477 = errorMgr.defineErrorInfo(
            477,
            "BUSINESS_ERROR_00477", 
            "���J���i�̃T�C�Y���s���ł��B");

    /**
     * ���J���i�f�B�X�J�E���g���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00478 = errorMgr.defineErrorInfo(
            478,
            "BUSINESS_ERROR_00478", 
            "���J���i�f�B�X�J�E���g���̃T�C�Y��������2���^������2���ȓ��͈̔͊O�ł��B");

    /**
     * ���I���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00479 = errorMgr.defineErrorInfo(
            479,
            "BUSINESS_ERROR_00479", 
            "�X�P�W���[������ς̏ꍇ�A���I���͕K�{���͍��ڂł��B");

    /**
     * �w���\�����ԁi�ژ_�����j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00480 = errorMgr.defineErrorInfo(
            480,
            "BUSINESS_ERROR_00480", 
            "�X�P�W���[������ς̏ꍇ�A�w���\�����ԁi�ژ_�����j�͕K�{���͍��ڂł��B");

    /**
     * �w���\�����ԁi���Ўw��j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00482 = errorMgr.defineErrorInfo(
            482,
            "BUSINESS_ERROR_00482", 
            "�X�P�W���[������ς̏ꍇ�A�w���\�����ԁi���Ўw��j�͕K�{���͍��ڂł��B");

    /**
     * ���s��Ѓ��S�t�@@�C��URL�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00484 = errorMgr.defineErrorInfo(
            484,
            "BUSINESS_ERROR_00484", 
            "���s��Ѓ��S�t�@@�C��URL�̃T�C�Y���s���ł��B");

    /**
     * ���s��ЃE�F�u�T�C�gURL�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00485 = errorMgr.defineErrorInfo(
            485,
            "BUSINESS_ERROR_00485", 
            "���s��ЃE�F�u�T�C�gURL�̃T�C�Y���s���ł��B");

    /**
     * ���s��ЊT�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00486 = errorMgr.defineErrorInfo(
            486,
            "BUSINESS_ERROR_00486", 
            "���s��ЊT�v�̃T�C�Y���s���ł��B");

    /**
     * ���l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00487 = errorMgr.defineErrorInfo(
            487,
            "BUSINESS_ERROR_00487", 
            "���l�̃T�C�Y���s���ł��B");

    /**
     * �����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00488 = errorMgr.defineErrorInfo(
            488,
            "BUSINESS_ERROR_00488", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A�����������l�������l�ł͂���܂���B");

    /**
     * ����������l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00489 = errorMgr.defineErrorInfo(
            489,
            "BUSINESS_ERROR_00489", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A����������l�������l�ł͂���܂���B");

    /**
     * �c�Ɠ������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00490 = errorMgr.defineErrorInfo(
            490,
            "BUSINESS_ERROR_00490", 
            "�c�Ɠ����������l�ȊO�̒l�ł��B");

    /**
     * �\�����i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00491 = errorMgr.defineErrorInfo(
            491,
            "BUSINESS_ERROR_00491", 
            "�\�����i�����l�ȊO�̒l�ł��B");

    /**
     * �\�����i�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00492 = errorMgr.defineErrorInfo(
            492,
            "BUSINESS_ERROR_00492", 
            "�\�����i�敪���g���s�h�̏ꍇ�́A�\�����i�w��s�B");

    /**
     * �\�����i�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00493 = errorMgr.defineErrorInfo(
            493,
            "BUSINESS_ERROR_00493", 
            "�w�l�ł��\�������ꍇ�́A�\�����i�����w�肵�Ă��������B");

    /**
     * �\�����ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00494 = errorMgr.defineErrorInfo(
            494,
            "BUSINESS_ERROR_00494", 
            "�\�����ʂ����l�ȊO�̒l�ł��B");

    /**
     * IPO�o�^�敪���g�����h�̏ꍇ�́AIPO�������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00495 = errorMgr.defineErrorInfo(
            495,
            "BUSINESS_ERROR_00495", 
            "IPO�o�^�敪���g�����h�̏ꍇ�́AIPO�������擾�ł��܂���B");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00496 = errorMgr.defineErrorInfo(
            496,
            "BUSINESS_ERROR_00496", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i�����o�^���C�u�b�N�r���f�B���O�J�n�����j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00497 = errorMgr.defineErrorInfo(
            497,
            "BUSINESS_ERROR_00497", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i�������񎦓��C�u�b�N�r���f�B���O�J�n�����j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00498 = errorMgr.defineErrorInfo(
            498,
            "BUSINESS_ERROR_00498", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i�u�b�N�r���f�B���O�J�n�����C�u�b�N�r���f�B���O�I�������j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00499 = errorMgr.defineErrorInfo(
            499,
            "BUSINESS_ERROR_00499", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i�u�b�N�r���f�B���O�I�������C���J���i������j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00500 = errorMgr.defineErrorInfo(
            500,
            "BUSINESS_ERROR_00500", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i���J���i������C���I���j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00501 = errorMgr.defineErrorInfo(
            501,
            "BUSINESS_ERROR_00501", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i���I���C�w���\���J�n���i�ژ_�����L�ځj�j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00502 = errorMgr.defineErrorInfo(
            502,
            "BUSINESS_ERROR_00502", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i�w���\���J�n�����i���Аݒ�j�j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00503 = errorMgr.defineErrorInfo(
            503,
            "BUSINESS_ERROR_00503", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i�w���\���I�������i���Аݒ�j�j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00504 = errorMgr.defineErrorInfo(
            504,
            "BUSINESS_ERROR_00504", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i�w���\���I�������i�ژ_�����L�ځj�j");

    /**
     * IPO�X�P�W���[�����ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00505 = errorMgr.defineErrorInfo(
            505,
            "BUSINESS_ERROR_00505", 
            "IPO�X�P�W���[�����ڂ̓��͂��s���ł��B�i���J���j");

    /**
     * ���ԏd���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00506 = errorMgr.defineErrorInfo(
            506,
            "BUSINESS_ERROR_00506", 
            "IPO���ԁi�u�b�N�r���f�B���O�J�n�����\���J���j���d�����Ă�����������ɓo�^����Ă��܂��B");

    /**
     * �\���ς݂̌ڋq�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00510 = errorMgr.defineErrorInfo(
            510,
            "BUSINESS_ERROR_00510", 
            "�\���ς݂̌ڋq�ł͂���܂���B");

    /**
     * �ڋq�ɊY������IPO�\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00511 = errorMgr.defineErrorInfo(
            511,
            "BUSINESS_ERROR_00511", 
            "IPO�\�����L���łȂ��ꍇ�AIPO�\���i�V�K���I�j�s�B");

    /**
     * �ڋq�ɊY������IPO�\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00512 = errorMgr.defineErrorInfo(
            512,
            "BUSINESS_ERROR_00512", 
            "IPO�\�������I���ʍX�V�ς݂̏ꍇ�AIPO�\���i�V�K���I�j�s�B");

    /**
     * �ڋq�ɊY������IPO�\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00513 = errorMgr.defineErrorInfo(
            513,
            "BUSINESS_ERROR_00513", 
            "���I���ʂ��\�����ʂ𒴂��Ă���ꍇ�AIPO�\���i�V�K���I�j�s�B");

    /**
     * �ڋq�ɊY������IPO�\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00514 = errorMgr.defineErrorInfo(
            514,
            "BUSINESS_ERROR_00514", 
            "�⌇���I��IPO�\�����w���\���ς݂łȂ��ꍇ�AIPO�\���i�J�㒊�I�j�s�B");

    /**
     * �ڋq�ɊY������IPO�\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00515 = errorMgr.defineErrorInfo(
            515,
            "BUSINESS_ERROR_00515", 
            "���ɌJ�㒊�I���ʂ��X�V����Ă���ꍇ�AIPO�\���i�J�㒊�I�j�s�B");

    /**
     * �D�揇�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00516 = errorMgr.defineErrorInfo(
            516,
            "BUSINESS_ERROR_00516", 
            "�D�揇�ʃ`�F�b�N�G���[�B");

    /**
     * �d���ڋq�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00517 = errorMgr.defineErrorInfo(
            517,
            "BUSINESS_ERROR_00517", 
            "�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");

    /**
     * ���I���ʂ��\�����ʂ𒴂��Ă��Ȃ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00518 = errorMgr.defineErrorInfo(
            518,
            "BUSINESS_ERROR_00518", 
            "�\�����ʂ͓��I���ʂ�菬�����ł��B");

    /**
     * ���I���ʂ̒l�����������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00519 = errorMgr.defineErrorInfo(
            519,
            "BUSINESS_ERROR_00519", 
            "�V�K���I�̒��I���ʂ̒l���s���ȃR�[�h�l�ł��B");

    /**
     * ���I���ʂ̒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00520 = errorMgr.defineErrorInfo(
            520,
            "BUSINESS_ERROR_00520", 
            "�J�㒊�I�̒��I���ʂ̒l���s���ȃR�[�h�l�ł��B");

    /**
     * ���I���ʁ^���I���ʂ̊֘A�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00521 = errorMgr.defineErrorInfo(
            521,
            "BUSINESS_ERROR_00521", 
            "�J�㒊�I�̒��I���ʂ̒l���s���ł��B�i�⌇���I�҂ɓ��I���ʂ��������Ă���j");

    /**
     * �L�[�w�b�_������̑Ó����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00522 = errorMgr.defineErrorInfo(
            522,
            "BUSINESS_ERROR_00522", 
            "�L�[�w�b�_������̑Ó����`�F�b�N�G���[�B�i�w�b�_�̃t�H�[�}�b�g���s���ł��j");

    /**
     * �L�[�w�b�_������̑Ó����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00523 = errorMgr.defineErrorInfo(
            523,
            "BUSINESS_ERROR_00523", 
            "�L�[�w�b�_������̑Ó����`�F�b�N�G���[�B�i�����R�[�h�`�F�b�N�G���[�j");

    /**
     * �L�[�w�b�_������̑Ó����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00524 = errorMgr.defineErrorInfo(
            524,
            "BUSINESS_ERROR_00524", 
            "�L�[�w�b�_������̑Ó����`�F�b�N�G���[�B�i�V�K�^���I�敪�`�F�b�N�G���[�j");

    /**
     * �A�b�v���[�h���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00525 = errorMgr.defineErrorInfo(
            525,
            "BUSINESS_ERROR_00525", 
            "�A�b�v���[�h���ԃ`�F�b�N�G���[�B�i�V�K���I�F�w���\�����Ԃ��I�����Ă���j");

    /**
     * �A�b�v���[�h���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00526 = errorMgr.defineErrorInfo(
            526,
            "BUSINESS_ERROR_00526", 
            "�A�b�v���[�h���ԃ`�F�b�N�G���[�B�i�J�㒊�I�F�w���\�����Ԃ��J�n���Ă��Ȃ��j");

    /**
     * ���I���ʃA�b�v���[�h�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00527 = errorMgr.defineErrorInfo(
            527,
            "BUSINESS_ERROR_00527", 
            "���I���ʃA�b�v���[�h�\��Ԃ̃`�F�b�N�G���[�B�i�u�b�N�r���f�B���O���Ԗ��I���j");

    /**
     * ���I���ʃA�b�v���[�h�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00528 = errorMgr.defineErrorInfo(
            528,
            "BUSINESS_ERROR_00528", 
            "IPO�̃X�P�W���[��������ł��B");

    /**
     * ���I���ʃA�b�v���[�h�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00529 = errorMgr.defineErrorInfo(
            529,
            "BUSINESS_ERROR_00529", 
            "���I���ʃA�b�v���[�h�\��Ԃ̃`�F�b�N�G���[�B�i���J���i�����肵�Ă��Ȃ��j");

    /**
     * �A�b�v���[�h���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00530 = errorMgr.defineErrorInfo(
            530,
            "BUSINESS_ERROR_00530", 
            "�A�b�v���[�h���Ԃ̃`�F�b�N�G���[�B�i�V�K���I�F�V�K���I���I�����Ă���j");

    /**
     * ���ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00531 = errorMgr.defineErrorInfo(
            531,
            "BUSINESS_ERROR_00531", 
            "�����̐��ʂ�0�ȉ��̒l�ł��B");

    /**
     * �P�ʐ��ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00532 = errorMgr.defineErrorInfo(
            532,
            "BUSINESS_ERROR_00532", 
            "�����̐��ʂ��w���\���P�ʂ̐����{�ł͂���܂���B");

    /**
     * �������敪�ɂ�郌���W�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00533 = errorMgr.defineErrorInfo(
            533,
            "BUSINESS_ERROR_00533", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A�����̒P���̃T�C�Y���s���ł��B");

    /**
     * �������敪�ɂ�郌���W�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00534 = errorMgr.defineErrorInfo(
            534,
            "BUSINESS_ERROR_00534", 
            "�������敪���g�����i�i�~�j�h�̏ꍇ�́A�����̒P���������l�ł͂���܂���B");

    /**
     * �������敪�ɂ�郌���W�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00535 = errorMgr.defineErrorInfo(
            535,
            "BUSINESS_ERROR_00535", 
            "�������敪���g�f�B�X�J�E���g���i���j�h�̏ꍇ�́A�����̒P���̃T�C�Y��������2���^������2���ȓ��̃����W�ł͂���܂���B");

    /**
     * ����������^�����l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00536 = errorMgr.defineErrorInfo(
            536,
            "BUSINESS_ERROR_00536", 
            "�����̒P���͉����������l���牼��������l�͈̔͂ł͂���܂���B");

    /**
     * ���݂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00537 = errorMgr.defineErrorInfo(
            537,
            "BUSINESS_ERROR_00537", 
            "�P���Ɖ����������l�Ƃ̍��z�͍��݂̐����{�ł͂���܂���B");

    /**
     * ��d�\������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00538 = errorMgr.defineErrorInfo(
            538,
            "BUSINESS_ERROR_00538", 
            "�Y���ڋq�͊��Ƀu�b�N�r���f�B���O�\���ςł��B");

    /**
     * IPO�������u�b�N�r���f�B���O�戵���ł��邱�Ƃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00539 = errorMgr.defineErrorInfo(
            539,
            "BUSINESS_ERROR_00539", 
            "���݁A���YIPO�����̓u�b�N�r���f�B���O����t���Ă���܂���B");

    /**
     * �ژ_�����d�q��t�σ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00540 = errorMgr.defineErrorInfo(
            540,
            "BUSINESS_ERROR_00540", 
            "�ژ_�����d�q��t���ςł��B");

    /**
     * ��d�w���\���^���ނ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00541 = errorMgr.defineErrorInfo(
            541,
            "BUSINESS_ERROR_00541", 
            "�Y��IPO�\���͓�d�w���\���^���ނł��B");

    /**
     * �w���\�����ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00542 = errorMgr.defineErrorInfo(
            542,
            "BUSINESS_ERROR_00542", 
            "IPO�������w���\�����ԊO�ł��B");

    /**
     * ���މ\���ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00543 = errorMgr.defineErrorInfo(
            543,
            "BUSINESS_ERROR_00543", 
            "IPO���������މ\���ԊO�ł��B");

    /**
     * ���I�^�⌇���I�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00544 = errorMgr.defineErrorInfo(
            544,
            "BUSINESS_ERROR_00544", 
            "�Y��IPO�\���͓��I�^�⌇���I�łȂ��ꍇ�A���邢�́A�Y���ڋq�͌J�㒊�I�ŗ��I���Ă���ꍇ�A�w���\���^���މ\���s�ł��B");

    /**
     * �w���\�����ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00546 = errorMgr.defineErrorInfo(
            546,
            "BUSINESS_ERROR_00546", 
            "�w���\�����ʂ͓��I���ʂ��傫���ł��B");

    /**
     * �������ڃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00547 = errorMgr.defineErrorInfo(
            547,
            "BUSINESS_ERROR_00547", 
            "�������ꂽ���ڂ��������܂���B�iIPO���������j");

    /**
     * ����ւ̕ύX�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00548 = errorMgr.defineErrorInfo(
            548,
            "BUSINESS_ERROR_00548", 
            "����ς̃X�P�W���[���𖢒�ɒ����s�ł��B");

    /**
     * �u�b�N�r���f�B���O�I�������̒����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00549 = errorMgr.defineErrorInfo(
            549,
            "BUSINESS_ERROR_00549", 
            "�u�b�N�r���f�B���O�I�����������ݓ����ȍ~�ɒ�������Ă����ꍇ�A�������IPO�����̃X�P�W���[��������ł��B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00550 = errorMgr.defineErrorInfo(
            550,
            "BUSINESS_ERROR_00550", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́AIPO�o�^�敪������ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00551 = errorMgr.defineErrorInfo(
            551,
            "BUSINESS_ERROR_00551", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́AIPO�o�^�敪�ڍׂ�����ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00552 = errorMgr.defineErrorInfo(
            552,
            "BUSINESS_ERROR_00552", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A������������ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00553 = errorMgr.defineErrorInfo(
            553,
            "BUSINESS_ERROR_00553", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A���J�s�������ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00554 = errorMgr.defineErrorInfo(
            554,
            "BUSINESS_ERROR_00554", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A�������敪������ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00555 = errorMgr.defineErrorInfo(
            555,
            "BUSINESS_ERROR_00555", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A����������l���폜�ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00556 = errorMgr.defineErrorInfo(
            556,
            "BUSINESS_ERROR_00556", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A�����������l���폜�ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00557 = errorMgr.defineErrorInfo(
            557,
            "BUSINESS_ERROR_00557", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A�������񎦓�������ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00558 = errorMgr.defineErrorInfo(
            558,
            "BUSINESS_ERROR_00558", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A�w���\���P�ʂ�����ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00559 = errorMgr.defineErrorInfo(
            559,
            "BUSINESS_ERROR_00559", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A���݂�����ł��܂���B");

    /**
     * �A�b�v���[�h���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00560 = errorMgr.defineErrorInfo(
            560,
            "BUSINESS_ERROR_00560", 
            "�A�b�v���[�h���ԃ`�F�b�N�G���[�B�i�J�㒊�I�F���J�����߂��Ă���j");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00561 = errorMgr.defineErrorInfo(
            561,
            "BUSINESS_ERROR_00561", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A�\���p�P�ʋ敪������ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00562 = errorMgr.defineErrorInfo(
            562,
            "BUSINESS_ERROR_00562", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A�劲���،���Ж�������ł��܂���B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00563 = errorMgr.defineErrorInfo(
            563,
            "BUSINESS_ERROR_00563", 
            "�u�b�N�r���f�B���O���Ԃ��n�܂��Ă�������́A�u�b�N�r���f�B���O�J�n����������ł��܂���B");

    /**
     * �����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00564 = errorMgr.defineErrorInfo(
            564,
            "BUSINESS_ERROR_00564", 
            "�V�K���I�I�������߂��Ă�������́A���J��������ł��܂���B");

    /**
     * �����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00565 = errorMgr.defineErrorInfo(
            565,
            "BUSINESS_ERROR_00565", 
            "�V�K���I�I�������߂��Ă�������́A����������l������ł��܂���B");

    /**
     * �����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00566 = errorMgr.defineErrorInfo(
            566,
            "BUSINESS_ERROR_00566", 
            "�V�K���I�I�������߂��Ă�������́A�����������l������ł��܂���B");

    /**
     * �����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00567 = errorMgr.defineErrorInfo(
            567,
            "BUSINESS_ERROR_00567", 
            "�V�K���I�I�������߂��Ă�������́A�u�b�N�r���f�B���O�I������������ł��܂���B");

    /**
     * �����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00568 = errorMgr.defineErrorInfo(
            568,
            "BUSINESS_ERROR_00568", 
            "�V�K���I�I�������߂��Ă�������́A���J���i�����������ł��܂���B");

    /**
     * �����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00569 = errorMgr.defineErrorInfo(
            569,
            "BUSINESS_ERROR_00569", 
            "�V�K���I�I�������߂��Ă�������́A���I��������ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00570 = errorMgr.defineErrorInfo(
            570,
            "BUSINESS_ERROR_00570", 
            "���J�����߂��Ă�������́A�����R�[�h������ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00571 = errorMgr.defineErrorInfo(
            571,
            "BUSINESS_ERROR_00571", 
            "���J�����߂��Ă�������́A���吔�ʂ�����ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00572 = errorMgr.defineErrorInfo(
            572,
            "BUSINESS_ERROR_00572", 
            "���J�����߂��Ă�������́A���o���ʂ�����ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00573 = errorMgr.defineErrorInfo(
            573,
            "BUSINESS_ERROR_00573", 
            "���J�����߂��Ă�������́A���Ў戵���ʂ�����ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00574 = errorMgr.defineErrorInfo(
            574,
            "BUSINESS_ERROR_00574", 
            "���J�����߂��Ă�������́A���s��Ѓ��S�t�@@�C��URL������ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00575 = errorMgr.defineErrorInfo(
            575,
            "BUSINESS_ERROR_00575", 
            "���J�����߂��Ă�������́A���s��ЃE�F�u�T�C�gURL������ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00576 = errorMgr.defineErrorInfo(
            576,
            "BUSINESS_ERROR_00576", 
            "���J�����߂��Ă�������́A���s��ЊT�v������ł��܂���B");

    /**
     * �����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00577 = errorMgr.defineErrorInfo(
            577,
            "BUSINESS_ERROR_00577", 
            "���J�����߂��Ă�������́A���l������ł��܂���B");

    /**
     * �����\���Ԃ��A�w���\���J�n���i�ژ_�����L�ځj�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00578 = errorMgr.defineErrorInfo(
            578,
            "BUSINESS_ERROR_00578", 
            "�w���\���J�n���i�ژ_�����L�ځj���߂��Ă�������́A�w���\���J�n���i�ژ_�����L�ځj������ł��܂���B");

    /**
     * �����\���Ԃ��A�w���\���I�����i�ژ_�����L�ځj�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00579 = errorMgr.defineErrorInfo(
            579,
            "BUSINESS_ERROR_00579", 
            "�w���\���I�����i�ژ_�����L�ځj���߂��Ă�������́A�w���\���I�����i�ژ_�����L�ځj������ł��܂���B");

    /**
     * �����\���Ԃ��A�w���\���J�n�����i���Аݒ�j�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00580 = errorMgr.defineErrorInfo(
            580,
            "BUSINESS_ERROR_00580", 
            "�w���\���J�n�����i���Аݒ�j���߂��Ă�������́A�w���\���J�n�����i���Аݒ�j������ł��܂���B");

    /**
     * �����\���Ԃ��A�w���\���I�������i���Аݒ�j�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00581 = errorMgr.defineErrorInfo(
            581,
            "BUSINESS_ERROR_00581", 
            "�w���\���I�������i���Аݒ�j���߂��Ă�������́A�w���\���I�������i���Аݒ������ł��܂���B");

    /**
     * �����\���Ԃ��A�i�u�b�N�r���f�B���O�J�n�����\�V�K���I�I���j�̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00582 = errorMgr.defineErrorInfo(
            582,
            "BUSINESS_ERROR_00582", 
            "�u�b�N�r���f�B���O�J�n�����\�V�K���I�I�������߂��Ă�������́A���J���i������ł��܂���B");

    /**
     * �����\���Ԃ��A�i�u�b�N�r���f�B���O�J�n�����\�w���\���J�n�����i���Аݒ�j�j�̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00583 = errorMgr.defineErrorInfo(
            583,
            "BUSINESS_ERROR_00583", 
            "�u�b�N�r���f�B���O�J�n�����\�w���\���J�n�����i���Аݒ�j���߂��Ă�������́A���J���i������ł��܂���B");

    /**
     * �����\���Ԃ��A�i�u�b�N�r���f�B���O�J�n�����\�w���\���J�n�����i���Аݒ�j�j�̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00584 = errorMgr.defineErrorInfo(
            584,
            "BUSINESS_ERROR_00584", 
            "�u�b�N�r���f�B���O�J�n�����\�w���\���J�n�����i���Аݒ�j���߂��Ă�������́A���J���i�i�f�B�X�J�E���g���j������ł��܂���B");

    /**
     * IPO���~�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00585 = errorMgr.defineErrorInfo(
            585,
            "BUSINESS_ERROR_00585", 
            "IPO���~�\��Ԃ̃`�F�b�N�G���[�B�i�X�P�W���[������Ŋ��A��~���łȂ��j");

    /**
     * IPO���~�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00586 = errorMgr.defineErrorInfo(
            586,
            "BUSINESS_ERROR_00586", 
            "IPO���~�\��Ԃ̃`�F�b�N�G���[�B�i�X�P�W���[������ςŊ��A�w���\�����I�����Ă��Ȃ��Ŋ��A��~���łȂ��j");

    /**
     * �u�b�N�r���f�B���O�_�E�����[�h�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00587 = errorMgr.defineErrorInfo(
            587,
            "BUSINESS_ERROR_00587", 
            "�u�b�N�r���f�B���O�_�E�����[�h�\��Ԃ̃`�F�b�N�G���[�B�i�u�b�N�r���f�B���O�J�n�O�j");

    /**
     * �폜�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00588 = errorMgr.defineErrorInfo(
            588,
            "BUSINESS_ERROR_00588", 
            "�������폜����Ă��܂��B");

    /**
     * ��W���~�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00589 = errorMgr.defineErrorInfo(
            589,
            "BUSINESS_ERROR_00589", 
            "��W���~�����ł��B");

    /**
     * ��W��~�^�ĊJ�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00590 = errorMgr.defineErrorInfo(
            590,
            "BUSINESS_ERROR_00590", 
            "��W��~�^�ĊJ�\��Ԃ̃`�F�b�N�G���[�B�iIPO�X�P�W���[������ϊ��A�w���\�����Ԃ��I�����Ă���j");

    /**
     * ���s�\�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00591 = errorMgr.defineErrorInfo(
            591,
            "BUSINESS_ERROR_00591", 
            "���s�\�����w��ł��B");

    /**
     * ���s�\�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00592 = errorMgr.defineErrorInfo(
            592,
            "BUSINESS_ERROR_00592", 
            "���s�\�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���茈��敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00593 = errorMgr.defineErrorInfo(
            593,
            "BUSINESS_ERROR_00593", 
            "���茈��敪�����w��ł��B");

    /**
     * ���茈��敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00594 = errorMgr.defineErrorInfo(
            594,
            "BUSINESS_ERROR_00594", 
            "���茈��敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �������e�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00595 = errorMgr.defineErrorInfo(
            595,
            "BUSINESS_ERROR_00595", 
            "���ʁA�P���ɒ���������Ă��܂���B");

    /**
     * ����X�P�W���[���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00596 = errorMgr.defineErrorInfo(
            596,
            "BUSINESS_ERROR_00596", 
            "����̏ꍇ�ɓo�^�����������ړo�^�ɖ���������܂��B");

    /**
     * �A�b�v���[�h���ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00597 = errorMgr.defineErrorInfo(
            597,
            "BUSINESS_ERROR_00597", 
            "�A�b�v���[�h���ԃ`�F�b�N�G���[�B�i�J�㒊�I�F�V�K���I���I�����Ă��Ȃ��j");

    /**
     * ���I���ʍ��v�l�͓��Ў戵���ʂ��傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00598 = errorMgr.defineErrorInfo(
            598,
            "BUSINESS_ERROR_00598", 
            "���I���ʍ��v�l�͓��Ў戵���ʂ��傫���ł��B");

    /**
     * ���I���ʍ��v�l�@@���@@�����\���ʂƂȂ�܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00599 = errorMgr.defineErrorInfo(
            599,
            "BUSINESS_ERROR_00599", 
            "���I���ʍ��v�l�@@���@@�����\���ʂƂȂ�܂��B");

    /**
     * �����h�c�̂m�t�k�k�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00600 = errorMgr.defineErrorInfo(
            600,
            "BUSINESS_ERROR_00600", 
            "����ID�����w��ł��B");

    /**
     * ����敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00601 = errorMgr.defineErrorInfo(
            601,
            "BUSINESS_ERROR_00601", 
            "����敪�����w��ł��B");

    /**
     * ����敪�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00602 = errorMgr.defineErrorInfo(
            602,
            "BUSINESS_ERROR_00602", 
            "����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �ٍσ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00603 = errorMgr.defineErrorInfo(
            603,
            "BUSINESS_ERROR_00603", 
            "�ٍς����w��ł��B");

    /**
     * �����敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00604 = errorMgr.defineErrorInfo(
            604,
            "BUSINESS_ERROR_00604", 
            "�����敪�����w��ł��B");

    /**
     * �����敪�̒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00605 = errorMgr.defineErrorInfo(
            605,
            "BUSINESS_ERROR_00605", 
            "�����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �s��R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00608 = errorMgr.defineErrorInfo(
            608,
            "BUSINESS_ERROR_00608", 
            "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���ό����ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00610 = errorMgr.defineErrorInfo(
            610,
            "BUSINESS_ERROR_00610", 
            "���ό����ꗗ�����w��ł��B");

    /**
     * ���ό����ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00611 = errorMgr.defineErrorInfo(
            611,
            "BUSINESS_ERROR_00611", 
            "���ό����ꗗ��0�ȉ��̒l�ł��B");

    /**
     * ���ό����ꗗ�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00612 = errorMgr.defineErrorInfo(
            612,
            "BUSINESS_ERROR_00612", 
            "���ό����ꗗ�̗v�f�����̑S�Ă̒������������w��ł��B");

    /**
     * �v���y�[�W�ԍ��`�F�b�N3�i�v���y�[�W�ԍ���0�ȉ��j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00616 = errorMgr.defineErrorInfo(
            616,
            "BUSINESS_ERROR_00616", 
            "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");

    /**
     * �y�[�W���\���s���`�F�b�N3�i�y�[�W���\���s����0�ȉ��j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00617 = errorMgr.defineErrorInfo(
            617,
            "BUSINESS_ERROR_00617", 
            "�y�[�W���\���s���̒l��0�ȉ��ł��B");

    /**
     * ���Ϗ����敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00618 = errorMgr.defineErrorInfo(
            618,
            "BUSINESS_ERROR_00618", 
            "���Ϗ����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���Ϗ��ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00619 = errorMgr.defineErrorInfo(
            619,
            "BUSINESS_ERROR_00619", 
            "���Ϗ����敪���g0�F�����_���h�̏ꍇ�́A���ό����ꗗ�̗v�f�����̑S�Ă̌��Ϗ��ʂ����w��ł��B");

    /**
     * ���Ϗ��ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00620 = errorMgr.defineErrorInfo(
            620,
            "BUSINESS_ERROR_00620", 
            "���Ϗ����敪���g0�F�����_���h�̏ꍇ�́A���ϑΏۂȂ��B");

    /**
     * ���Ϗ��ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00621 = errorMgr.defineErrorInfo(
            621,
            "BUSINESS_ERROR_00621", 
            "�����_���ԍψȊO�͒��������A���Ϗ��ʂ̎w��s�B");

    /**
     * �������n�������敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00622 = errorMgr.defineErrorInfo(
            622,
            "BUSINESS_ERROR_00622", 
            "�����挻�n�������敪�����w��ł��B");

    /**
     * �������n�������敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00623 = errorMgr.defineErrorInfo(
            623,
            "BUSINESS_ERROR_00623", 
            "�����挻�n�������敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���Ϗ��ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00624 = errorMgr.defineErrorInfo(
            624,
            "BUSINESS_ERROR_00624", 
            "���Ϗ����敪��null�܂��́A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�̏ꍇ�A�������������w��ł��B");

    /**
     * �o���]�̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00625 = errorMgr.defineErrorInfo(
            625,
            "BUSINESS_ERROR_00625", 
            "�o���]�̓G���[�B");

    /**
     * ����ԋ敪�`�F�b�N�i����ԋ敪������`�̒l�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00626 = errorMgr.defineErrorInfo(
            626,
            "BUSINESS_ERROR_00626", 
            "����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �ٍϋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00628 = errorMgr.defineErrorInfo(
            628,
            "BUSINESS_ERROR_00628", 
            "�ٍϋ敪�����w��ł��B");

    /**
     * �ٍϋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00629 = errorMgr.defineErrorInfo(
            629,
            "BUSINESS_ERROR_00629", 
            "�ٍϋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �ٍϊ����l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00630 = errorMgr.defineErrorInfo(
            630,
            "BUSINESS_ERROR_00630", 
            "�ٍϊ����l�����w��ł��B");

    /**
     * �ٍϊ����l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00631 = errorMgr.defineErrorInfo(
            631,
            "BUSINESS_ERROR_00631", 
            "�ٍϊ����l�������ȊO�̒l�ł��B");

    /**
     * �ٍϊ����l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00632 = errorMgr.defineErrorInfo(
            632,
            "BUSINESS_ERROR_00632", 
            "�ٍϊ����l��0�ȉ��̒l�ł��B");

    /**
     * ���Y�����͊��ɑS�����ρB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00634 = errorMgr.defineErrorInfo(
            634,
            "BUSINESS_ERROR_00634", 
            "���Y�����͊��ɑS�����ρB");

    /**
     * ���v��萔�ʂ����������𒴂��Ă��Ȃ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00635 = errorMgr.defineErrorInfo(
            635,
            "BUSINESS_ERROR_00635", 
            "���v��萔�ʂ��������ʂ𒴂��Ă��܂��B");

    /**
     * �Y�������͕ύX�^����̎�t�ς܂��͔������̏�ԁB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00636 = errorMgr.defineErrorInfo(
            636,
            "BUSINESS_ERROR_00636", 
            "�Y�������͕ύX�^����̎�t�ς܂��͔������̏�Ԃł��B");

    /**
     * ��������������J�݁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00637 = errorMgr.defineErrorInfo(
            637,
            "BUSINESS_ERROR_00637", 
            "�����̓�������J�݂Ȃ��B");

    /**
     * �w������͎w��s��ł̎戵�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00638 = errorMgr.defineErrorInfo(
            638,
            "BUSINESS_ERROR_00638", 
            "�w������͎w��s��ł̎戵�s�B");

    /**
     * ����敪�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00639 = errorMgr.defineErrorInfo(
            639,
            "BUSINESS_ERROR_00639", 
            "����敪�̒l���s���ł��B�i�����`�F�b�N�j");

    /**
     * ����敪�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00640 = errorMgr.defineErrorInfo(
            640,
            "BUSINESS_ERROR_00640", 
            "����敪�̒l���s���ł��B�i�����`�F�b�N�j");

    /**
     * �s���ȕٍϋ敪�iSONAR�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00641 = errorMgr.defineErrorInfo(
            641,
            "BUSINESS_ERROR_00641", 
            "�s���ȕٍϋ敪�iSONAR�j�B");

    /**
     * �戵�\�s��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00643 = errorMgr.defineErrorInfo(
            643,
            "BUSINESS_ERROR_00643", 
            "�戵�\�s�ꂪ���݂��Ȃ��B");

    /**
     * ��戵�s��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00645 = errorMgr.defineErrorInfo(
            645,
            "BUSINESS_ERROR_00645", 
            "��戵�s��G���[�B");

    /**
     * �戵�\�ٍςȂ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00646 = errorMgr.defineErrorInfo(
            646,
            "BUSINESS_ERROR_00646", 
            "�戵�\�ٍςȂ��B");

    /**
     * �����ΏۊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00649 = errorMgr.defineErrorInfo(
            649,
            "BUSINESS_ERROR_00649", 
            "��������ʒm�敪�̒l���s���ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00650 = errorMgr.defineErrorInfo(
            650,
            "BUSINESS_ERROR_00650", 
            "���������`�F�b�N�G���[�B");

    /**
     * �����^��������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00651 = errorMgr.defineErrorInfo(
            651,
            "BUSINESS_ERROR_00651", 
            "�f�[�^�s�����ɂ�锃���^��������s�B");

    /**
     * �����J�e�S���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00653 = errorMgr.defineErrorInfo(
            653,
            "BUSINESS_ERROR_00653", 
            "�����J�e�S���̒l���s���ł��B");

    /**
     * ���ω\�c���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00657 = errorMgr.defineErrorInfo(
            657,
            "BUSINESS_ERROR_00657", 
            "�������������ω\�c�����傫���ł��B");

    /**
     * �K�{�v���p�e�B�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00658 = errorMgr.defineErrorInfo(
            658,
            "BUSINESS_ERROR_00658", 
            "�����o���ʒm�L���[�̕K�{�ݒ荀�ڂ����w��ł��B");

    /**
     * �����P��ID�ɊY�����錚���ԍώw������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00659 = errorMgr.defineErrorInfo(
            659,
            "BUSINESS_ERROR_00659", 
            "�����P��ID�ɊY�����錚���ԍώw������擾�ł��܂���B");

    /**
     * �����ꗗ���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00660 = errorMgr.defineErrorInfo(
            660,
            "BUSINESS_ERROR_00660", 
            "�����ꗗ���擾�ł��܂���B");

    /**
     * �ٍϊ����l����s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00661 = errorMgr.defineErrorInfo(
            661,
            "BUSINESS_ERROR_00661", 
            "�ٍϊ����l����s�B");

    /**
     * �����A�戵�\�s��`�F�b�N�i��戵�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00664 = errorMgr.defineErrorInfo(
            664,
            "BUSINESS_ERROR_00664", 
            "��戵�����G���[�B");

    /**
     * ����]�͕s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00667 = errorMgr.defineErrorInfo(
            667,
            "BUSINESS_ERROR_00667", 
            "����]�͕s���G���[�B�i�M�p�V�K���\�z�s���j");

    /**
     * ����\�z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00668 = errorMgr.defineErrorInfo(
            668,
            "BUSINESS_ERROR_00668", 
            "����\�z�s���G���[�B");

    /**
     * �Y������⏕�����Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00670 = errorMgr.defineErrorInfo(
            670,
            "BUSINESS_ERROR_00670", 
            "�Y������⏕�����Ȃ��B");

    /**
     * ����ς݂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00675 = errorMgr.defineErrorInfo(
            675,
            "BUSINESS_ERROR_00675", 
            "�w��̒��������Ɏ���ς݂ł��B");

    /**
     * ���Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00676 = errorMgr.defineErrorInfo(
            676,
            "BUSINESS_ERROR_00676", 
            "����Ώۂ̖��f�[�^�����݂��Ȃ��B");

    /**
     * ����Ώۖ��f�[�^�̓���s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00677 = errorMgr.defineErrorInfo(
            677,
            "BUSINESS_ERROR_00677", 
            "����Ώۖ��f�[�^�̓���s�G���[�B");

    /**
     * ������ʒ��߃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00682 = errorMgr.defineErrorInfo(
            682,
            "BUSINESS_ERROR_00682", 
            "�����͎w��\��������l���z���Ă��܂��B");

    /**
     * �����J�z�X�L�b�v�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00684 = errorMgr.defineErrorInfo(
            684,
            "BUSINESS_ERROR_00684", 
            "�����J�z�X�L�b�v�����̃`�F�b�N�G���[�B�i�����J�z�s�̖����j");

    /**
     * �������ړ��������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00687 = errorMgr.defineErrorInfo(
            687,
            "BUSINESS_ERROR_00687", 
            "�������ړ��������s�B");

    /**
     * �t�w�l��l�A�����������Z�q�ύX�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00690 = errorMgr.defineErrorInfo(
            690,
            "BUSINESS_ERROR_00690", 
            "�s�ꔭ���ϒ����̋t�w�l��l�y�сA�����������Z�q�͒����s�B");

    /**
     * ���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00691 = errorMgr.defineErrorInfo(
            691,
            "BUSINESS_ERROR_00691", 
            "�����Ԓ��̃`�F�b�N�G���[�B");

    /**
     * �����s�i�����s�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00692 = errorMgr.defineErrorInfo(
            692,
            "BUSINESS_ERROR_00692", 
            "�����s�i�����s�����j�B");

    /**
     * ��ʐM�p�̎戵�s�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00693 = errorMgr.defineErrorInfo(
            693,
            "BUSINESS_ERROR_00693", 
            "��ʐM�p�̎戵�s�����B");

    /**
     * �����s�i��ݎؖ����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00694 = errorMgr.defineErrorInfo(
            694,
            "BUSINESS_ERROR_00694", 
            "�����s�i��ݎؖ����j�B");

    /**
     * ��ݎؖ����̎戵�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00695 = errorMgr.defineErrorInfo(
            695,
            "BUSINESS_ERROR_00695", 
            "��ݎؖ����̎戵�s�B");

    /**
     * ���x�M�p�̎戵�s�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00696 = errorMgr.defineErrorInfo(
            696,
            "BUSINESS_ERROR_00696", 
            "���x�M�p�̎戵�s�����B");

    /**
     * �戵�\�`�F�b�N�i�M�p����̎戵�s�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00697 = errorMgr.defineErrorInfo(
            697,
            "BUSINESS_ERROR_00697", 
            "�M�p����̎戵�s�����B");

    /**
     * ������~�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00700 = errorMgr.defineErrorInfo(
            700,
            "BUSINESS_ERROR_00700", 
            "�����̔�����~��(������K��/���ЋK��)�B");

    /**
     * �戵�\�s��`�F�b�N�G���[(���X�E�ٍ�)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00703 = errorMgr.defineErrorInfo(
            703,
            "BUSINESS_ERROR_00703", 
            "�戵�\�s��`�F�b�N�G���[(���X�E�ٍ�)�B");

    /**
     * �o����܂Œ����戵�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00704 = errorMgr.defineErrorInfo(
            704,
            "BUSINESS_ERROR_00704", 
            "�o����܂Œ����戵�̃`�F�b�N�G���[�B");

    /**
     * �K�i�@@�֓����Ƃ̐V�K�������̐��s�����͕s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00705 = errorMgr.defineErrorInfo(
            705,
            "BUSINESS_ERROR_00705", 
            "�K�i�@@�֓����Ƃ̐V�K�������̐��s�����͕s�B");

    /**
     * HOST����t�\�Ȋ�������l�𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00707 = errorMgr.defineErrorInfo(
            707,
            "BUSINESS_ERROR_00707", 
            "HOST����t�\�Ȋ�������l�𒴂��Ă��܂��B");

    /**
     * �����������P�ʂ̐����{�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00708 = errorMgr.defineErrorInfo(
            708,
            "BUSINESS_ERROR_00708", 
            "�����������P�ʂ̐����{�ł͂���܂���B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00709 = errorMgr.defineErrorInfo(
            709,
            "BUSINESS_ERROR_00709", 
            "������0�ȉ��̒l�ł��B");

    /**
     * ��ʌ����̌����͓�������ւ̍�������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00710 = errorMgr.defineErrorInfo(
            710,
            "BUSINESS_ERROR_00710", 
            "��ʌ����̌����͓�������ւ̍�������s�B");

    /**
     * ���s�w��K���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00712 = errorMgr.defineErrorInfo(
            712,
            "BUSINESS_ERROR_00712", 
            "�M�p����E���s�w��K�����B");

    /**
     * JASDAQ�������s�w��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00713 = errorMgr.defineErrorInfo(
            713,
            "BUSINESS_ERROR_00713", 
            "JASDAQ�����͐��s�w��s�B");

    /**
     * ����������e�Ó����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00716 = errorMgr.defineErrorInfo(
            716,
            "BUSINESS_ERROR_00716", 
            "�Y��������SONAR���M���ςł��B");

    /**
     * �~�j�������R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00717 = errorMgr.defineErrorInfo(
            717,
            "BUSINESS_ERROR_00717", 
            "���Y�����͂��戵�ΏۊO�ł��B");

    /**
     * �~�j����������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00718 = errorMgr.defineErrorInfo(
            718,
            "BUSINESS_ERROR_00718", 
            "���Y�����͊����~�j�����̑ΏۊO�ł��B");

    /**
     * �~�j����������~����Ă��Ȃ����Ƃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00719 = errorMgr.defineErrorInfo(
            719,
            "BUSINESS_ERROR_00719", 
            "���Y�����͊����~�j�����̎����~���ł��B");

    /**
     * ������ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00720 = errorMgr.defineErrorInfo(
            720,
            "BUSINESS_ERROR_00720", 
            "�~�j�����t������������������𒴂��Ă��܂��B");

    /**
     * �����P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00721 = errorMgr.defineErrorInfo(
            721,
            "BUSINESS_ERROR_00721", 
            "�~�j�����t�����������~�j�������P�ʂ̐����{�ł͂���܂���B");

    /**
     * �����P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00723 = errorMgr.defineErrorInfo(
            723,
            "BUSINESS_ERROR_00723", 
            "�~�j�������P�ʂ��s���ł��B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00724 = errorMgr.defineErrorInfo(
            724,
            "BUSINESS_ERROR_00724", 
            "���t�������Ԓ��ɒP�ʊ����ȏ㒍������܂����B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00725 = errorMgr.defineErrorInfo(
            725,
            "BUSINESS_ERROR_00725", 
            "���t�������Ԓ��ɒP�ʊ��̔�����������܂����B");

    /**
     * W�w�l�����łȂ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00726 = errorMgr.defineErrorInfo(
            726,
            "BUSINESS_ERROR_00726", 
            "W�w�l�����ł͂���܂���B");

    /**
     * �~�j���́A������ɓ�������𔄔��ł��Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00728 = errorMgr.defineErrorInfo(
            728,
            "BUSINESS_ERROR_00728", 
            "�~�j���̏ꍇ�́A������ɓ�������̒������d���ł��܂���B");

    /**
     * �w������̋󔄂�K�����ʂ𒴉߃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00734 = errorMgr.defineErrorInfo(
            734,
            "BUSINESS_ERROR_00734", 
            "�w������̋󔄂�K�����ʂ𒴂��Ă��܂��B");

    /**
     * ��������擾�ł��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00735 = errorMgr.defineErrorInfo(
            735,
            "BUSINESS_ERROR_00735", 
            "�w�肵���s��A�w����ʁA�����A�v�b�g�^�R�[���A�s�g���i�̑g�ݍ��킹�͎�舵���Ă��܂���B");

    /**
     * STOP���l���̏ꍇ�́A�萔�����i�R�[�h���w��K�v�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00736 = errorMgr.defineErrorInfo(
            736,
            "BUSINESS_ERROR_00736", 
            "STOP���l���̏ꍇ�́A�萔�����i�R�[�h���w��K�v�ł��B");

    /**
     * ���o�������P�ʂ��f�[�^�s�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00739 = errorMgr.defineErrorInfo(
            739,
            "BUSINESS_ERROR_00739", 
            "���o�������P�ʂ��f�[�^�s�����ł��B");

    /**
     * �U�։\�񐔂𒴂��ĂȂ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00740 = errorMgr.defineErrorInfo(
            740,
            "BUSINESS_ERROR_00740", 
            "�U�։񐔂�����񐔂𒴂��Ă��܂��B");

    /**
     * ������̏���l����(��)�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00741 = errorMgr.defineErrorInfo(
            741,
            "BUSINESS_ERROR_00741", 
            "������̏���l����(��)�G���[�B");

    /**
     * ������̏���l����(�����P��)�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00742 = errorMgr.defineErrorInfo(
            742,
            "BUSINESS_ERROR_00742", 
            "������̏���l����(�����P��)�G���[�B");

    /**
     * ������̏���l����(���)�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00743 = errorMgr.defineErrorInfo(
            743,
            "BUSINESS_ERROR_00743", 
            "������̏���l����(���)�G���[�B");

    /**
     * ���n�Ώۂۗ̕L���Y�̐��ʕs���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00744 = errorMgr.defineErrorInfo(
            744,
            "BUSINESS_ERROR_00744", 
            "���n�Ώۂۗ̕L���Y�̐��ʕs���G���[�B");

    /**
     * ���Y���X�ł́A�戵���s�\�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00746 = errorMgr.defineErrorInfo(
            746,
            "BUSINESS_ERROR_00746", 
            "���Y���X�ł́A�戵���s�\�ł��B");

    /**
     * ���Y�ڋq���A�w��̐M�p����������J�݂��Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00747 = errorMgr.defineErrorInfo(
            747,
            "BUSINESS_ERROR_00747", 
            "���Y�ڋq���A�w��̐M�p����������J�݂��Ă��܂���B");

    /**
     * �������ϊ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00748 = errorMgr.defineErrorInfo(
            748,
            "BUSINESS_ERROR_00748", 
            "�Y�������̔����������ϊ����𒴂��Ă��܂��B");

    /**
     * ���ϋ@@�֎�t���ԃe�[�u������̃G���[���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00751 = errorMgr.defineErrorInfo(
            751,
            "BUSINESS_ERROR_00751", 
            "�w�肵�����ϋ@@�ւ̎�t���ԂɊւ����񂪎擾�ł��܂���ł����B");

    /**
     * �������z�P�ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00752 = errorMgr.defineErrorInfo(
            752,
            "BUSINESS_ERROR_00752", 
            "�������z�P�ʃG���[�B");

    /**
     * 1��������̓����񐔂�����񐔂��z���ĂȂ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00753 = errorMgr.defineErrorInfo(
            753,
            "BUSINESS_ERROR_00753", 
            "����̍ő�����񐔃G���[�B");

    /**
     * 1��������̓������z�𒴂��ĂȂ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00754 = errorMgr.defineErrorInfo(
            754,
            "BUSINESS_ERROR_00754", 
            "����̍ő�������z�G���[�B");

    /**
     * ��Еʎ�����z�f�[�^���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00755 = errorMgr.defineErrorInfo(
            755,
            "BUSINESS_ERROR_00755", 
            "��Еʎ�����z�f�[�^���擾�ł��܂���B");

    /**
     * �o��������z�͈͊O�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00756 = errorMgr.defineErrorInfo(
            756,
            "BUSINESS_ERROR_00756", 
            "�o��������z�͈͊O�G���[�B");

    /**
     * �����̎�n���Ɠ������ɂ��łɏo���������o�ĂȂ����ǂ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00757 = errorMgr.defineErrorInfo(
            757,
            "BUSINESS_ERROR_00757", 
            "�w�肵�����U���\����Ɠ����U�����̏o���\�������łɓo�^����Ă��܂��B");

    /**
     * �T�[�r�X�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00758 = errorMgr.defineErrorInfo(
            758,
            "BUSINESS_ERROR_00758", 
            "�T�[�r�X�敪�����w��ł��B");

    /**
     * �U�֋��z�����͂���Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00759 = errorMgr.defineErrorInfo(
            759,
            "BUSINESS_ERROR_00759", 
            "�U�֋��z�����͂���Ă��܂���B");

    /**
     * �U�֋��z���\�z�𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00761 = errorMgr.defineErrorInfo(
            761,
            "BUSINESS_ERROR_00761", 
            "�U�֋��z���\�z�𒴂��Ă��܂��B");

    /**
     * ���ϋ@@��ID�ɊY�������Еʌ��ϋ@@�֏������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00763 = errorMgr.defineErrorInfo(
            763,
            "BUSINESS_ERROR_00763", 
            "���ϋ@@��ID�ɊY�������Еʌ��ϋ@@�֏������擾�ł��܂���B");

    /**
     * ���Z�@@�֓��o���󋵃��R�[�h�̃f�[�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00764 = errorMgr.defineErrorInfo(
            764,
            "BUSINESS_ERROR_00764", 
            "���Z�@@�֓��o���󋵃��R�[�h�̃f�[�^�G���[�B");

    /**
     * �������z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00766 = errorMgr.defineErrorInfo(
            766,
            "BUSINESS_ERROR_00766", 
            "�������z���͒l�G���[�B");

    /**
     * ���ϋ@@��ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00767 = errorMgr.defineErrorInfo(
            767,
            "BUSINESS_ERROR_00767", 
            "���ϋ@@��ID�̓��͂��s���ł��B");

    /**
     * AP�w�Z�b�V����ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00769 = errorMgr.defineErrorInfo(
            769,
            "BUSINESS_ERROR_00769", 
            "AP�w�Z�b�V����ID�����w��ł��B");

    /**
     * �m�F������ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00770 = errorMgr.defineErrorInfo(
            770,
            "BUSINESS_ERROR_00770", 
            "�m�F������ID�����w��ł��B");

    /**
     * �o�����z���͒l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00771 = errorMgr.defineErrorInfo(
            771,
            "BUSINESS_ERROR_00771", 
            "�o�����z���͒l�G���[�B");

    /**
     * �U���\����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00772 = errorMgr.defineErrorInfo(
            772,
            "BUSINESS_ERROR_00772", 
            "�U���\��������w��ł��B");

    /**
     * ���ߐU�������U���\����ƂȂ��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00773 = errorMgr.defineErrorInfo(
            773,
            "BUSINESS_ERROR_00773", 
            "���ߐU�������U���\����ƂȂ��Ă��܂��B");

    /**
     * �o���������J�݃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00774 = errorMgr.defineErrorInfo(
            774,
            "BUSINESS_ERROR_00774", 
            "�o���������J�݃G���[�B");

    /**
     * �U�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00775 = errorMgr.defineErrorInfo(
            775,
            "BUSINESS_ERROR_00775", 
            "�����U���N�������͒l�G���[�B");

    /**
     * �U������Z�@@�փR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00776 = errorMgr.defineErrorInfo(
            776,
            "BUSINESS_ERROR_00776", 
            "�������Z�@@�փR�[�h���͒l�G���[�B");

    /**
     * ���[���A�h���X�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00777 = errorMgr.defineErrorInfo(
            777,
            "BUSINESS_ERROR_00777", 
            "���[���A�h���X�`�F�b�N�G���[�B");

    /**
     * ���Z�@@�ւ��擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00778 = errorMgr.defineErrorInfo(
            778,
            "BUSINESS_ERROR_00778", 
            "�U������Z�@@�փR�[�h�ɊY��������Z�@@�ւ��擾�ł��܂���B");

    /**
     * ���X�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00779 = errorMgr.defineErrorInfo(
            779,
            "BUSINESS_ERROR_00779", 
            "���X�R�[�h�̓��͂��s���ł��B");

    /**
     * �ڋq�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00780 = errorMgr.defineErrorInfo(
            780,
            "BUSINESS_ERROR_00780", 
            "�ڋq�R�[�h�̓��͂��s���ł��B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00781 = errorMgr.defineErrorInfo(
            781,
            "BUSINESS_ERROR_00781", 
            "�������i���j�͒������i���j���傫���ł��B");

    /**
     * .com�f�r�b�g���ώ���ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00782 = errorMgr.defineErrorInfo(
            782,
            "BUSINESS_ERROR_00782", 
            ".com�f�r�b�g���ώ���ԍ��i���j�̓��͂��s���ł��B");

    /**
     * .com�f�r�b�g���ώ���ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00783 = errorMgr.defineErrorInfo(
            783,
            "BUSINESS_ERROR_00783", 
            ".com�f�r�b�g���ώ���ԍ��i���j��.com�f�r�b�g���ώ���ԍ��i���j���傫���ł��B");

    /**
     * ������ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00784 = errorMgr.defineErrorInfo(
            784,
            "BUSINESS_ERROR_00784", 
            "������Ԃ����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���݃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00785 = errorMgr.defineErrorInfo(
            785,
            "BUSINESS_ERROR_00785", 
            "�ڋq�R�[�h�A�������i���j�A�������i���j�A��n���A.com�f�r�b�g���ώ���ԍ��i���j�̂��ׂĂ����w��ł��B");

    /**
     * �v���y�[�W�ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00786 = errorMgr.defineErrorInfo(
            786,
            "BUSINESS_ERROR_00786", 
            "�v���y�[�W�ԍ��̓��͂��s���ł��B");

    /**
     * .com�f�r�b�g���ώ���ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00787 = errorMgr.defineErrorInfo(
            787,
            "BUSINESS_ERROR_00787", 
            ".com�f�r�b�g���ώ���ԍ��i���j�̓��͂��s���ł��B");

    /**
     * .com�f�r�b�g���ώ���ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00788 = errorMgr.defineErrorInfo(
            788,
            "BUSINESS_ERROR_00788", 
            ".com�f�r�b�g���ώ���ԍ��i���j�����w��̏ꍇ�A.com�f�r�b�g���ώ���ԍ��i���j���w��s�ł��B");

    /**
     * �ڋq�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00789 = errorMgr.defineErrorInfo(
            789,
            "BUSINESS_ERROR_00789", 
            "�ڋq�R�[�h�i���j�̓��͂��s���ł��B");

    /**
     * ������t�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00790 = errorMgr.defineErrorInfo(
            790,
            "BUSINESS_ERROR_00790", 
            "������t�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �w�����X�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00793 = errorMgr.defineErrorInfo(
            793,
            "BUSINESS_ERROR_00793", 
            "�w�����X�g�̗v�f����0�ł��B");

    /**
     * �ڋq�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00794 = errorMgr.defineErrorInfo(
            794,
            "BUSINESS_ERROR_00794", 
            "�ڋq�R�[�h�i���j�̓��͂��s���ł��B");

    /**
     * �ڋq�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00795 = errorMgr.defineErrorInfo(
            795,
            "BUSINESS_ERROR_00795", 
            "�ڋq�R�[�h�i���j�͌ڋq�R�[�h�i���j���傫���ł��B");

    /**
     * �A�������`�F�b�N.�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00796 = errorMgr.defineErrorInfo(
            796,
            "BUSINESS_ERROR_00796", 
            "�A�������i���j�͘A�������i���j���傫���ł��B");

    /**
     * �A�������`�F�b�N.�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00797 = errorMgr.defineErrorInfo(
            797,
            "BUSINESS_ERROR_00797", 
            "�A�������i���j�����w��̏ꍇ�A�A�������i���j���w��s�ł��B");

    /**
     * �U�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00799 = errorMgr.defineErrorInfo(
            799,
            "BUSINESS_ERROR_00799", 
            "�U�����i���j�͐U�����i���j���傫���ł��B");

    /**
     * �U�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00800 = errorMgr.defineErrorInfo(
            800,
            "BUSINESS_ERROR_00800", 
            "�U�����i���j�����w��̏ꍇ�A�U�����i���j���w��s�ł��B");

    /**
     * �o�͋敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00801 = errorMgr.defineErrorInfo(
            801,
            "BUSINESS_ERROR_00801", 
            "�o�͋敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �v���y�[�W�ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00802 = errorMgr.defineErrorInfo(
            802,
            "BUSINESS_ERROR_00802", 
            "�o�͋敪���g�ꗗ�h�̏ꍇ�́A�v���y�[�W�ԍ��̓��͂��s���ł��B");

    /**
     * �v���y�[�W�ԍ��`�F�b�N.�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00803 = errorMgr.defineErrorInfo(
            803,
            "BUSINESS_ERROR_00803", 
            "�o�͋敪���g�ꗗ�h�̏ꍇ�́A�v���y�[�W�ԍ��������ȊO�̒l�ł��B");

    /**
     * �y�[�W���\���s���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00804 = errorMgr.defineErrorInfo(
            804,
            "BUSINESS_ERROR_00804", 
            "�o�͋敪���g�ꗗ�h�̏ꍇ�́A�y�[�W���\���s���̓��͂��s���ł��B");

    /**
     * �y�[�W���\���s���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00805 = errorMgr.defineErrorInfo(
            805,
            "BUSINESS_ERROR_00805", 
            "�o�͋敪���g�ꗗ�h�̏ꍇ�́A�y�[�W���\���s���������ȊO�̒l�ł��B");

    /**
     * �o����܂Œ����͎��s�����̒����s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00806 = errorMgr.defineErrorInfo(
            806,
            "BUSINESS_ERROR_00806", 
            "�o����܂Œ����͎��s�����̒����s�B");

    /**
     * �،���Ђ��戵�s�Ȏ��s�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00807 = errorMgr.defineErrorInfo(
            807,
            "BUSINESS_ERROR_00807", 
            "�،���Ђ��戵�s�Ȏ��s�����B");

    /**
     * �����c���s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00808 = errorMgr.defineErrorInfo(
            808,
            "BUSINESS_ERROR_00808", 
            "�����c���s���G���[�B");

    /**
     * �U�֋��z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00809 = errorMgr.defineErrorInfo(
            809,
            "BUSINESS_ERROR_00809", 
            "�U�֋��z���O�܂��̓}�C�i�X�l�ł��B");

    /**
     * �U�֋��z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00810 = errorMgr.defineErrorInfo(
            810,
            "BUSINESS_ERROR_00810", 
            "�U�֋��z�̃T�C�Y���s���ł��B");

    /**
     * �U�֋��z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00811 = errorMgr.defineErrorInfo(
            811,
            "BUSINESS_ERROR_00811", 
            "�U�֋��z�ɐ����ȊO�̕��������͂���Ă��܂��B");

    /**
     * �����̒����^����̎�t���\���ǂ������肷��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00812 = errorMgr.defineErrorInfo(
            812,
            "BUSINESS_ERROR_00812", 
            "�s��ǌ�\�،���Ж��ɒʒm���󂯂čs���o���I���ʒm�I���܂ł̊Ԃ́A���������^�����t��s�Ƃ��܂��B");

    /**
     * �����R�[�h�������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00815 = errorMgr.defineErrorInfo(
            815,
            "BUSINESS_ERROR_00815", 
            "�����R�[�h�������ȊO�̒l�ł��B");

    /**
     * �����󋵋敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00816 = errorMgr.defineErrorInfo(
            816,
            "BUSINESS_ERROR_00816", 
            "�����󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���s�����ύX�`�F�b�N(JASDAQ�����͎��s�����̒����s��)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00819 = errorMgr.defineErrorInfo(
            819,
            "BUSINESS_ERROR_00819", 
            "JASDAQ�����͎��s�����̒����s�B");

    /**
     * �����L����Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00820 = errorMgr.defineErrorInfo(
            820,
            "BUSINESS_ERROR_00820", 
            "�N���[�Y���������͓������̌������n�����������Ď���ł��܂���B");

    /**
     * �����̒������\��������Ԃł��邩�ǂ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00821 = errorMgr.defineErrorInfo(
            821,
            "BUSINESS_ERROR_00821", 
            "�����J�e�S�����g�������n�����h�̏ꍇ�́A�����s�B");

    /**
     * ���ϐ��ʂ������c���ʂ𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00824 = errorMgr.defineErrorInfo(
            824,
            "BUSINESS_ERROR_00824", 
            "���ϐ��ʂ������c���ʂ𒴉߁B");

    /**
     * ���Z�@@�֎�t���ԃ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00826 = errorMgr.defineErrorInfo(
            826,
            "BUSINESS_ERROR_00826", 
            "���Z�@@�֎�t���ԃ`�F�b�N�G���[�B");

    /**
     * �،���ЃR�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00827 = errorMgr.defineErrorInfo(
            827,
            "BUSINESS_ERROR_00827", 
            "�،���ЃR�[�h�����w��ł��B");

    /**
     * ���ʃR�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00829 = errorMgr.defineErrorInfo(
            829,
            "BUSINESS_ERROR_00829", 
            "���ʃR�[�h�����w��ł��B");

    /**
     * ���̓p�����[�^�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00830 = errorMgr.defineErrorInfo(
            830,
            "BUSINESS_ERROR_00830", 
            "���̓p�����[�^�`�F�b�N�G���[�B");

    /**
     * �T�[�r�X�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00831 = errorMgr.defineErrorInfo(
            831,
            "BUSINESS_ERROR_00831", 
            "�T�[�r�X�敪�̌������s���ł��B");

    /**
     * �\���o�^ID�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00832 = errorMgr.defineErrorInfo(
            832,
            "BUSINESS_ERROR_00832", 
            "�\���o�^ID�����w��ł��B");

    /**
     * ���X�R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00833 = errorMgr.defineErrorInfo(
            833,
            "BUSINESS_ERROR_00833", 
            "���X�R�[�h�����w��ł��B");

    /**
     * ���X�R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00834 = errorMgr.defineErrorInfo(
            834,
            "BUSINESS_ERROR_00834", 
            "���X�R�[�h�̃T�C�Y���s���ł��B");

    /**
     * �ڋq�R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00835 = errorMgr.defineErrorInfo(
            835,
            "BUSINESS_ERROR_00835", 
            "�ڋq�R�[�h�����w��ł��B");

    /**
     * �ڋq�R�[�h�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00836 = errorMgr.defineErrorInfo(
            836,
            "BUSINESS_ERROR_00836", 
            "�ڋq�R�[�h�̃T�C�Y���s���ł��B");

    /**
     * �K�p�J�n���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00837 = errorMgr.defineErrorInfo(
            837,
            "BUSINESS_ERROR_00837", 
            "�K�p�J�n�������w��ł��B");

    /**
     * �K�p�I�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00838 = errorMgr.defineErrorInfo(
            838,
            "BUSINESS_ERROR_00838", 
            "�K�p�I���������w��ł��B");

    /**
     * �K�p�I�����s�K�؃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00839 = errorMgr.defineErrorInfo(
            839,
            "BUSINESS_ERROR_00839", 
            "�K�p�J�n�����K�p�I������薢�����t�ł��B");

    /**
     * �K�p�J�n���s�K�؃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00840 = errorMgr.defineErrorInfo(
            840,
            "BUSINESS_ERROR_00840", 
            "�\�������K�p�J�n����薢�����t�ł��B");

    /**
     * �o�^�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00841 = errorMgr.defineErrorInfo(
            841,
            "BUSINESS_ERROR_00841", 
            "�o�^�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���p�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00842 = errorMgr.defineErrorInfo(
            842,
            "BUSINESS_ERROR_00842", 
            "���p���������l�ȊO�̒l�ł��B");

    /**
     * ���p�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00843 = errorMgr.defineErrorInfo(
            843,
            "BUSINESS_ERROR_00843", 
            "���p�����̃T�C�Y���s���ł��B");

    /**
     * ���I�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00844 = errorMgr.defineErrorInfo(
            844,
            "BUSINESS_ERROR_00844", 
            "���I�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �\�����I�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00845 = errorMgr.defineErrorInfo(
            845,
            "BUSINESS_ERROR_00845", 
            "���I�敪�́g���h�łȂ��ꍇ�A�\�����I�敪�����w��ł��B");

    /**
     * �\�����I�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00846 = errorMgr.defineErrorInfo(
            846,
            "BUSINESS_ERROR_00846", 
            "���I�敪�́g���h�łȂ��ꍇ�A�\�����I�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �\�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00847 = errorMgr.defineErrorInfo(
            847,
            "BUSINESS_ERROR_00847", 
            "���I�敪�́g���h�łȂ��ꍇ�A�\���������w��ł��B");

    /**
     * �\�����I�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00849 = errorMgr.defineErrorInfo(
            849,
            "BUSINESS_ERROR_00849", 
            "�\�����I�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ����]�͕s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00855 = errorMgr.defineErrorInfo(
            855,
            "BUSINESS_ERROR_00855", 
            "����]�͕s���G���[�B�i�M�p�����\�z�s���j");

    /**
     * �A�b�v���[�h�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00856 = errorMgr.defineErrorInfo(
            856,
            "BUSINESS_ERROR_00856", 
            "�A�b�v���[�h�G���[�B");

    /**
     * DIR�Ǘ��Ҍ����`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00857 = errorMgr.defineErrorInfo(
            857,
            "BUSINESS_ERROR_00857", 
            "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");

    /**
     * �����L����Ԃ�OPEN�ȊO�̏ꍇ�͎���s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00858 = errorMgr.defineErrorInfo(
            858,
            "BUSINESS_ERROR_00858", 
            "�����L����Ԃ�OPEN�ȊO�̏ꍇ�͎���s�ł��B");

    /**
     * �f�[�^�s�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00859 = errorMgr.defineErrorInfo(
            859,
            "BUSINESS_ERROR_00859", 
            "���[����񂪃f�[�^�s�����ł��B");

    /**
     * ���M���[���敪�E����ID�d���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00860 = errorMgr.defineErrorInfo(
            860,
            "BUSINESS_ERROR_00860", 
            "���M���[���敪�E����ID�d���G���[�B");

    /**
     * ���M���[���敪�����̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00862 = errorMgr.defineErrorInfo(
            862,
            "BUSINESS_ERROR_00862", 
            "���M���[���敪�����̓G���[�B");

    /**
     * ���M���[���敪�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00863 = errorMgr.defineErrorInfo(
            863,
            "BUSINESS_ERROR_00863", 
            "���M���[���敪�������G���[�B");

    /**
     * ���M���[���敪�̒l�����l�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00864 = errorMgr.defineErrorInfo(
            864,
            "BUSINESS_ERROR_00864", 
            "���M���[���敪�̒l�����l�ł͂���܂���B");

    /**
     * ����ID�����̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00865 = errorMgr.defineErrorInfo(
            865,
            "BUSINESS_ERROR_00865", 
            "����ID�����̓G���[�B");

    /**
     * ����ID�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00866 = errorMgr.defineErrorInfo(
            866,
            "BUSINESS_ERROR_00866", 
            "����ID�������G���[�B");

    /**
     * ����ID�̒l�����p�p���ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00867 = errorMgr.defineErrorInfo(
            867,
            "BUSINESS_ERROR_00867", 
            "����ID�̒l�����p�p���ł͂���܂���B");

    /**
     * ���[�����̕������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00868 = errorMgr.defineErrorInfo(
            868,
            "BUSINESS_ERROR_00868", 
            "���[�����̕������G���[�B");

    /**
     * ���o�l�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00870 = errorMgr.defineErrorInfo(
            870,
            "BUSINESS_ERROR_00870", 
            "���o�l�������G���[�B");

    /**
     * ���������̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00871 = errorMgr.defineErrorInfo(
            871,
            "BUSINESS_ERROR_00871", 
            "���������̓G���[�B");

    /**
     * �����������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00872 = errorMgr.defineErrorInfo(
            872,
            "BUSINESS_ERROR_00872", 
            "�����������G���[�B");

    /**
     * ���[���w�b�_�[�A���[���{���A���[���t�b�^�[�����̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00873 = errorMgr.defineErrorInfo(
            873,
            "BUSINESS_ERROR_00873", 
            "���[���w�b�_�[�A���[���{���A���[���t�b�^�[�����̓G���[�B");

    /**
     * ���[���w�b�_�[�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00874 = errorMgr.defineErrorInfo(
            874,
            "BUSINESS_ERROR_00874", 
            "���[���w�b�_�[�������G���[�B");

    /**
     * ���[���{���������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00875 = errorMgr.defineErrorInfo(
            875,
            "BUSINESS_ERROR_00875", 
            "���[���{���������G���[�B");

    /**
     * ���[���t�b�^�[�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00876 = errorMgr.defineErrorInfo(
            876,
            "BUSINESS_ERROR_00876", 
            "���[���t�b�^�[�������G���[�B");

    /**
     * ���M��A�h���X�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00877 = errorMgr.defineErrorInfo(
            877,
            "BUSINESS_ERROR_00877", 
            "���M��A�h���X�������G���[�B");

    /**
     * ���I�ݒ肪�u���v�̏ꍇ�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00878 = errorMgr.defineErrorInfo(
            878,
            "BUSINESS_ERROR_00878", 
            "���I�̖����T�[�r�X�́A���p�\���̎�����ł��܂���B");

    /**
     * �o�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00879 = errorMgr.defineErrorInfo(
            879,
            "BUSINESS_ERROR_00879", 
            "�L���T�[�r�X�̏ꍇ�́A�o�����̎w��͕K�{�ł��B");

    /**
     * �T�[�r�X�敪�̒l�͐��l�ȊO�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00882 = errorMgr.defineErrorInfo(
            882,
            "BUSINESS_ERROR_00882", 
            "�T�[�r�X�敪�����l�ȊO�̒l�ł��B");

    /**
     * �T�[�r�X���̂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00883 = errorMgr.defineErrorInfo(
            883,
            "BUSINESS_ERROR_00883", 
            "�T�[�r�X���̂����w��ł��B");

    /**
     * �T�[�r�X���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00884 = errorMgr.defineErrorInfo(
            884,
            "BUSINESS_ERROR_00884", 
            "�T�[�r�X���̂̃T�C�Y���s���ł��B");

    /**
     * �E�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00885 = errorMgr.defineErrorInfo(
            885,
            "BUSINESS_ERROR_00885", 
            "�\���敪���g�v�h�̏ꍇ�́A�E�v�����w��ł��B");

    /**
     * �E�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00886 = errorMgr.defineErrorInfo(
            886,
            "BUSINESS_ERROR_00886", 
            "�\���敪���g�s�v�h�̏ꍇ�́A�E�v���w��s�ł��B");

    /**
     * �E�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00887 = errorMgr.defineErrorInfo(
            887,
            "BUSINESS_ERROR_00887", 
            "�E�v�̒l�ɔ��p�J�i�ȊO�̕������܂܂�Ă��܂��B");

    /**
     * �E�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00888 = errorMgr.defineErrorInfo(
            888,
            "BUSINESS_ERROR_00888", 
            "�E�v�̃T�C�Y���s���ł��B");

    /**
     * �X�e�[�^�X�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00889 = errorMgr.defineErrorInfo(
            889,
            "BUSINESS_ERROR_00889", 
            "�X�e�[�^�X�����w��ł��B");

    /**
     * �X�e�[�^�X�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00890 = errorMgr.defineErrorInfo(
            890,
            "BUSINESS_ERROR_00890", 
            "�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �\���敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00891 = errorMgr.defineErrorInfo(
            891,
            "BUSINESS_ERROR_00891", 
            "�\���敪�����w��ł��B");

    /**
     * �\���敪�̒l���Ⴂ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00892 = errorMgr.defineErrorInfo(
            892,
            "BUSINESS_ERROR_00892", 
            "�\���敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �ύX�ڋq�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00893 = errorMgr.defineErrorInfo(
            893,
            "BUSINESS_ERROR_00893", 
            "�ύX�ڋq�ꗗ�����w��ł��B");

    /**
     * 2�d�o�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00894 = errorMgr.defineErrorInfo(
            894,
            "BUSINESS_ERROR_00894", 
            "��d�o�^�G���[�B�i�T�[�r�X�\���o�^�j");

    /**
     * ���I�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00895 = errorMgr.defineErrorInfo(
            895,
            "BUSINESS_ERROR_00895", 
            "�\�����K�v�ȃT�[�r�X�́A���I�敪�̓��͕͂K�{�ł��B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00897 = errorMgr.defineErrorInfo(
            897,
            "BUSINESS_ERROR_00897", 
            "���p���Ԃ̓��͂��s���ł��B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00898 = errorMgr.defineErrorInfo(
            898,
            "BUSINESS_ERROR_00898", 
            "���p���Ԃ̓��͂�����܂���B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00899 = errorMgr.defineErrorInfo(
            899,
            "BUSINESS_ERROR_00899", 
            "���p���Ԃ̌�����0�ȉ��̒l�ł��B");

    /**
     * �����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00900 = errorMgr.defineErrorInfo(
            900,
            "BUSINESS_ERROR_00900", 
            "�����R�[�h��0�ȉ��̒l�ł��B");

    /**
     * ���������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00901 = errorMgr.defineErrorInfo(
            901,
            "BUSINESS_ERROR_00901", 
            "���������������ȊO�̒l�ł��B");

    /**
     * ���������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00902 = errorMgr.defineErrorInfo(
            902,
            "BUSINESS_ERROR_00902", 
            "����������0�ȉ��̒l�ł��B");

    /**
     * ���������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00903 = errorMgr.defineErrorInfo(
            903,
            "BUSINESS_ERROR_00903", 
            "���������̃T�C�Y���s���ł��B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00905 = errorMgr.defineErrorInfo(
            905,
            "BUSINESS_ERROR_00905", 
            "���p���Ԃ̌������������z���Ă��܂��B");

    /**
     * ���p�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00906 = errorMgr.defineErrorInfo(
            906,
            "BUSINESS_ERROR_00906", 
            "���p�����̓��͂�����܂���B");

    /**
     * ���p�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00907 = errorMgr.defineErrorInfo(
            907,
            "BUSINESS_ERROR_00907", 
            "���p�����̌������������z���Ă��܂��B");

    /**
     * �Ώۃ��R�[�h�Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00908 = errorMgr.defineErrorInfo(
            908,
            "BUSINESS_ERROR_00908", 
            "�T�[�r�X�\���o�^�f�[�^���擾�ł��܂���B");

    /**
     * ���p���R�[�h�ύX�s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00909 = errorMgr.defineErrorInfo(
            909,
            "BUSINESS_ERROR_00909", 
            "���p���R�[�h�ύX�s�G���[�B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00910 = errorMgr.defineErrorInfo(
            910,
            "BUSINESS_ERROR_00910", 
            "���p���Ԃ̎w��Ɍ�肪����܂��B");

    /**
     * ���p���ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00912 = errorMgr.defineErrorInfo(
            912,
            "BUSINESS_ERROR_00912", 
            "���p���Ԃ����l�ȊO�̒l�ł��B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00913 = errorMgr.defineErrorInfo(
            913,
            "BUSINESS_ERROR_00913", 
            "���p���Ԃ�0�ȉ��̒l�ł��B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00914 = errorMgr.defineErrorInfo(
            914,
            "BUSINESS_ERROR_00914", 
            "���p���Ԃ̌������������z���Ă��܂��B");

    /**
     * �\�����Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00916 = errorMgr.defineErrorInfo(
            916,
            "BUSINESS_ERROR_00916", 
            "�\�����Ԃ̓��͂Ɍ�肪����܂��B");

    /**
     * �\���\���ԁi���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00919 = errorMgr.defineErrorInfo(
            919,
            "BUSINESS_ERROR_00919", 
            "�\���\���ԁi���j�����l�ȊO�̒l�ł��B");

    /**
     * �\���\���ԁi���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00920 = errorMgr.defineErrorInfo(
            920,
            "BUSINESS_ERROR_00920", 
            "�\���\���ԁi���j�����l�ȊO�̒l�ł��B");

    /**
     * �\���\���ԁi���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00921 = errorMgr.defineErrorInfo(
            921,
            "BUSINESS_ERROR_00921", 
            "�\���\���ԁi���j��0�ȉ��̒l�ł��B");

    /**
     * �\���\���ԁi���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00922 = errorMgr.defineErrorInfo(
            922,
            "BUSINESS_ERROR_00922", 
            "�\���\���ԁi���j��0�ȉ��̒l�ł��B");

    /**
     * ���ӏ������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00923 = errorMgr.defineErrorInfo(
            923,
            "BUSINESS_ERROR_00923", 
            "�\�����s�v�ȃT�[�r�X�ł́A���ӏ������̎w��͂ł��܂���B");

    /**
     * �T�[�r�X���e�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00924 = errorMgr.defineErrorInfo(
            924,
            "BUSINESS_ERROR_00924", 
            "�\�����s�v�ȃT�[�r�X�ł́A�T�[�r�X���e�̎w��͂ł��܂���B");

    /**
     * �T�[�r�X�����iURL�j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00925 = errorMgr.defineErrorInfo(
            925,
            "BUSINESS_ERROR_00925", 
            "�\�����s�v�ȃT�[�r�X�ł́A�T�[�r�X����(URL)�̎w��͂ł��܂���B");

    /**
     * ���[���敪�i�m�F���[���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00926 = errorMgr.defineErrorInfo(
            926,
            "BUSINESS_ERROR_00926", 
            "�\�����K�v�ȃT�[�r�X�ł́A���[���敪(�m�F���[��)�̎w��͕K�{�ł��B");

    /**
     * ���[���敪�i�m�F���[���j�̒l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00927 = errorMgr.defineErrorInfo(
            927,
            "BUSINESS_ERROR_00927", 
            "���[���敪�i�m�F���[���j�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���[���敪�i�_��������[���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00928 = errorMgr.defineErrorInfo(
            928,
            "BUSINESS_ERROR_00928", 
            "�\�����K�v�ȃT�[�r�X�ł́A���[���敪(�_��������[��)�̎w��͕K�{�ł��B");

    /**
     * ���[���敪�i�_��������[���j�̒l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00929 = errorMgr.defineErrorInfo(
            929,
            "BUSINESS_ERROR_00929", 
            "���[���敪�i�_��������[���j�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���[�����M���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00930 = errorMgr.defineErrorInfo(
            930,
            "BUSINESS_ERROR_00930", 
            "���[�����M���̎w�肪����܂���B");

    /**
     * ��W���ԏ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00931 = errorMgr.defineErrorInfo(
            931,
            "BUSINESS_ERROR_00931", 
            "���I�������T�[�r�X�ł́A��W���ԂɊւ�����̎w��͂ł��܂���B");

    /**
     * �^�p�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00932 = errorMgr.defineErrorInfo(
            932,
            "BUSINESS_ERROR_00932", 
            "�^�p�敪�����w��ł��B");

    /**
     * �^�p�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00933 = errorMgr.defineErrorInfo(
            933,
            "BUSINESS_ERROR_00933", 
            "�^�p�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �\�����Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00934 = errorMgr.defineErrorInfo(
            934,
            "BUSINESS_ERROR_00934", 
            "�\�����Ԃ̓��͂��s���ł��B");

    /**
     * �K�p�J�n���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00936 = errorMgr.defineErrorInfo(
            936,
            "BUSINESS_ERROR_00936", 
            "�K�p�J�n���̓��͂��s���ł��B");

    /**
     * �K�p�I�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00937 = errorMgr.defineErrorInfo(
            937,
            "BUSINESS_ERROR_00937", 
            "�K�p�I�����̓��͂��s���ł��B");

    /**
     * �o�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00938 = errorMgr.defineErrorInfo(
            938,
            "BUSINESS_ERROR_00938", 
            "�o�����̓��͂��s���ł��B");

    /**
     * ��W�g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00939 = errorMgr.defineErrorInfo(
            939,
            "BUSINESS_ERROR_00939", 
            "��W�g�����l�ȊO�̒l�ł��B");

    /**
     * ��W�g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00940 = errorMgr.defineErrorInfo(
            940,
            "BUSINESS_ERROR_00940", 
            "���T�[�r�X�ł́A��W�g�̎w��͂ł��܂���B");

    /**
     * ��W�g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00941 = errorMgr.defineErrorInfo(
            941,
            "BUSINESS_ERROR_00941", 
            "���T�[�r�X�ł́A��W�g�̎w��͕K�{�ł��B");

    /**
     * ��W���ԏ��̗��p�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00942 = errorMgr.defineErrorInfo(
            942,
            "BUSINESS_ERROR_00942", 
            "���I���L��T�[�r�X�ł́A���p�����̎w��͕K�{�ł��B");

    /**
     * ��W���ԏ��̓��D�P�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00943 = errorMgr.defineErrorInfo(
            943,
            "BUSINESS_ERROR_00943", 
            "���T�[�r�X�ł́A���D�P�ʂ̎w��͂ł��܂���B");

    /**
     * ��W���ԏ��̓��D�P�ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00944 = errorMgr.defineErrorInfo(
            944,
            "BUSINESS_ERROR_00944", 
            "���T�[�r�X�ł́A���D�P�ʂ̎w��͕K�{�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00945 = errorMgr.defineErrorInfo(
            945,
            "BUSINESS_ERROR_00945", 
            "�������������w��ł��B");

    /**
     * �K�p���ԃ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00946 = errorMgr.defineErrorInfo(
            946,
            "BUSINESS_ERROR_00946", 
            "�K�p���ԃ`�F�b�N�G���[�B�i�T�[�r�X���I���`�F�b�N�j");

    /**
     * �o�����A���I���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00947 = errorMgr.defineErrorInfo(
            947,
            "BUSINESS_ERROR_00947", 
            "�o�����ɒ��I�����O�̓��t���w�肵���B");

    /**
     * �K�p�J�n���A�K�p�I�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00949 = errorMgr.defineErrorInfo(
            949,
            "BUSINESS_ERROR_00949", 
            "�K�p�J�n���ƓK�p�I�����̓��͂��s���ł��B");

    /**
     * ���I���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00950 = errorMgr.defineErrorInfo(
            950,
            "BUSINESS_ERROR_00950", 
            "���I���̓��͂�����܂���B");

    /**
     * �\�����ԁA���I���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00951 = errorMgr.defineErrorInfo(
            951,
            "BUSINESS_ERROR_00951", 
            "�\�����Ԃƒ��I���̓��͂��s���ł��B");

    /**
     * �K�p�J�n���A���I���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00952 = errorMgr.defineErrorInfo(
            952,
            "BUSINESS_ERROR_00952", 
            "�K�p�J�n���ƒ��I���̓��͂��s���ł��B");

    /**
     * ID�̌����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00954 = errorMgr.defineErrorInfo(
            954,
            "BUSINESS_ERROR_00954", 
            "ID�̃T�C�Y���s���ł��B");

    /**
     * ���[���敪�i�m�F���[���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00956 = errorMgr.defineErrorInfo(
            956,
            "BUSINESS_ERROR_00956", 
            "���[���敪�i�m�F���[���j�����w��ł��B");

    /**
     * ���I���ID�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00957 = errorMgr.defineErrorInfo(
            957,
            "BUSINESS_ERROR_00957", 
            "���I���ID�����w��ł��B");

    /**
     * �ō����D�z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00958 = errorMgr.defineErrorInfo(
            958,
            "BUSINESS_ERROR_00958", 
            "�ō����D�z�����l�ȊO�̒l�ł��B");

    /**
     * �ō����D�z�̌����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00959 = errorMgr.defineErrorInfo(
            959,
            "BUSINESS_ERROR_00959", 
            "�ō����D�z�̃T�C�Y���s���ł��B");

    /**
     * �Œᗎ�D�z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00960 = errorMgr.defineErrorInfo(
            960,
            "BUSINESS_ERROR_00960", 
            "�Œᗎ�D�z�����l�ȊO�̒l�ł��B");

    /**
     * �Œᗎ�D�z�̌����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00961 = errorMgr.defineErrorInfo(
            961,
            "BUSINESS_ERROR_00961", 
            "�Œᗎ�D�z�̃T�C�Y���s���ł��B");

    /**
     * ���d���ϊz�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00962 = errorMgr.defineErrorInfo(
            962,
            "BUSINESS_ERROR_00962", 
            "���d���ϊz�����l�ȊO�̒l�ł��B");

    /**
     * �A�b�v���[�hID�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00973 = errorMgr.defineErrorInfo(
            973,
            "BUSINESS_ERROR_00973", 
            "�A�b�v���[�hID�����w��ł��B");

    /**
     * ���d���ϊz�̌����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00974 = errorMgr.defineErrorInfo(
            974,
            "BUSINESS_ERROR_00974", 
            "���d���ϊz�̃T�C�Y���s���ł��B");

    /**
     * �ō����D�z�E�Œᗎ�D�z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00975 = errorMgr.defineErrorInfo(
            975,
            "BUSINESS_ERROR_00975", 
            "�Œᗎ�D�z���ō����D�z�ȏ�ł��B");

    /**
     * �A�b�v���[�h�t�@@�C���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00976 = errorMgr.defineErrorInfo(
            976,
            "BUSINESS_ERROR_00976", 
            "�A�b�v���[�h�t�@@�C�������w��ł��B");

    /**
     * ���D�z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00977 = errorMgr.defineErrorInfo(
            977,
            "BUSINESS_ERROR_00977", 
            "���D�z�����l�ȊO�̒l�ł��B");

    /**
     * ���D�z�̌����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00978 = errorMgr.defineErrorInfo(
            978,
            "BUSINESS_ERROR_00978", 
            "���D�z�̃T�C�Y���s���ł��B");

    /**
     * �\����ʋ敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00979 = errorMgr.defineErrorInfo(
            979,
            "BUSINESS_ERROR_00979", 
            "�\����ʋ敪�����w��ł��B");

    /**
     * �\����ʋ敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00980 = errorMgr.defineErrorInfo(
            980,
            "BUSINESS_ERROR_00980", 
            "�\����ʋ敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �T�[�r�X�}�X�^�[�f�[�^���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00982 = errorMgr.defineErrorInfo(
            982,
            "BUSINESS_ERROR_00982", 
            "�T�[�r�X�}�X�^�[�f�[�^���擾�ł��܂���B");

    /**
     * �\�����ԃ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00983 = errorMgr.defineErrorInfo(
            983,
            "BUSINESS_ERROR_00983", 
            "�\�����ԃ`�F�b�N�G���[�B�i�T�[�r�X���I���`�F�b�N�j");

    /**
     * �K�p���ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00984 = errorMgr.defineErrorInfo(
            984,
            "BUSINESS_ERROR_00984", 
            "�K�p���ԃ`�F�b�N�G���[�B�i�T�[�r�X�\���o�^�`�F�b�N�j");

    /**
     * ���@@�\�́ADIR�Ǘ��҂̂ݎ��s�ł��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00985 = errorMgr.defineErrorInfo(
            985,
            "BUSINESS_ERROR_00985", 
            "���@@�\�́ADIR�Ǘ��҂̂ݎ��s�ł��܂��B");

    /**
     * DIR�Ǘ��҂ł͂Ȃ��ꍇ�A���ݐ\�����Ԓ��̒��I���̕ҏW�͕s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00988 = errorMgr.defineErrorInfo(
            988,
            "BUSINESS_ERROR_00988", 
            "DIR�Ǘ��҂ł͂Ȃ��ꍇ�A���ݐ\�����Ԓ��̒��I���̕ҏW�͕s�B");

    /**
     * �ߋ��̒��I���̕ύX�͕s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00989 = errorMgr.defineErrorInfo(
            989,
            "BUSINESS_ERROR_00989", 
            "�ߋ��̒��I���̕ύX�͕s�B");

    /**
     * �c�Ɠ��ł͂Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00990 = errorMgr.defineErrorInfo(
            990,
            "BUSINESS_ERROR_00990", 
            "�o�������c�Ɠ��ł͂���܂���B");

    /**
     * �T�[�r�X���I��񗚗��̎擾�ł��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00991 = errorMgr.defineErrorInfo(
            991,
            "BUSINESS_ERROR_00991", 
            "�T�[�r�X���I��񗚗��f�[�^���擾�ł��܂���B");

    /**
     * ���׍s�i�A�b�v���[�h�f�[�^�j��ǉ����s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00992 = errorMgr.defineErrorInfo(
            992,
            "BUSINESS_ERROR_00992", 
            "���׍s�i�A�b�v���[�h�f�[�^�j��ǉ����s�B");

    /**
     * �\�����ݒ�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00993 = errorMgr.defineErrorInfo(
            993,
            "BUSINESS_ERROR_00993", 
            "�\�����ݒ�G���[�B");

    /**
     * �\�����I�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00995 = errorMgr.defineErrorInfo(
            995,
            "BUSINESS_ERROR_00995", 
            "�\���E���I�敪�̎w��Ɍ�肪����܂��B");

    /**
     * �T�[�r�X���\���\�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01000 = errorMgr.defineErrorInfo(
            1000,
            "BUSINESS_ERROR_01000", 
            "�T�[�r�X���\���\�ł͂���܂���B");

    /**
     * �\�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01001 = errorMgr.defineErrorInfo(
            1001,
            "BUSINESS_ERROR_01001", 
            "���I�������T�[�r�X�ł́A�\�����̎w��͂ł��܂���B");

    /**
     * ���݁A�L���ȃT�[�r�X�o�^������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01002 = errorMgr.defineErrorInfo(
            1002,
            "BUSINESS_ERROR_01002", 
            "���݁A�L���ȃT�[�r�X�o�^������܂���B");

    /**
     * �\�������A���I���̐\�����ԓ��ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01003 = errorMgr.defineErrorInfo(
            1003,
            "BUSINESS_ERROR_01003", 
            "�\�������A���I���̐\�����ԓ��ł͂���܂���B");

    /**
     * �����_���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01004 = errorMgr.defineErrorInfo(
            1004,
            "BUSINESS_ERROR_01004", 
            "�����_���`�F�b�N�G���[�B�i�������e�G���[�j");

    /**
     * �\�����I�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01007 = errorMgr.defineErrorInfo(
            1007,
            "BUSINESS_ERROR_01007", 
            "�T�[�r�X���p�\���̐\���E���I�敪�Ɍ�肪����܂��B");

    /**
     * �T�[�r�X���p�\���̎�����\�Ȑ\����񂪂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01009 = errorMgr.defineErrorInfo(
            1009,
            "BUSINESS_ERROR_01009", 
            "�T�[�r�X���p�\���̎�����\�Ȑ\����񂪂���܂���B");

    /**
     * �\������s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01010 = errorMgr.defineErrorInfo(
            1010,
            "BUSINESS_ERROR_01010", 
            "�\������s�G���[�B");

    /**
     * ���p�s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01011 = errorMgr.defineErrorInfo(
            1011,
            "BUSINESS_ERROR_01011", 
            "���p�s�G���[�B");

    /**
     * �����\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01012 = errorMgr.defineErrorInfo(
            1012,
            "BUSINESS_ERROR_01012", 
            "�����\���`�F�b�N�G���[�i�\����ʋ敪�F�ʏ�\���j�B");

    /**
     * �����\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01013 = errorMgr.defineErrorInfo(
            1013,
            "BUSINESS_ERROR_01013", 
            "�����\���`�F�b�N�G���[�i�\����ʋ敪�F�p���\���j�B");

    /**
     * �����\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01015 = errorMgr.defineErrorInfo(
            1015,
            "BUSINESS_ERROR_01015", 
            "�����\���`�F�b�N�G���[�i�\����ʋ敪�F���p�\���j�B");

    /**
     * ���p�\���s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01016 = errorMgr.defineErrorInfo(
            1016,
            "BUSINESS_ERROR_01016", 
            "���p�\���s�G���[�B");

    /**
     * �T�[�r�X�\���o�^�f�[�^�����ɑ��݂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01017 = errorMgr.defineErrorInfo(
            1017,
            "BUSINESS_ERROR_01017", 
            "�T�[�r�X�\���o�^�f�[�^�����ɑ��݂���B");

    /**
     * �T�[�r�X���I���f�[�^���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01018 = errorMgr.defineErrorInfo(
            1018,
            "BUSINESS_ERROR_01018", 
            "�T�[�r�X���I���f�[�^���擾�ł��܂���B");

    /**
     * ���D�z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01019 = errorMgr.defineErrorInfo(
            1019,
            "BUSINESS_ERROR_01019", 
            "���D�z�`�F�b�N�G���[�B");

    /**
     * �A�b�v���[�h�敪�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01020 = errorMgr.defineErrorInfo(
            1020,
            "BUSINESS_ERROR_01020", 
            "�A�b�v���[�h�敪�̒l���s���ł��B");

    /**
     * �\�����I�敪�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01022 = errorMgr.defineErrorInfo(
            1022,
            "BUSINESS_ERROR_01022", 
            "�\�����I�敪�G���[�B");

    /**
     * �،���ЃR�[�h�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01023 = errorMgr.defineErrorInfo(
            1023,
            "BUSINESS_ERROR_01023", 
            "�،���ЃR�[�h�̒l���s���ł��B");

    /**
     * ���o�^�ڋq�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01026 = errorMgr.defineErrorInfo(
            1026,
            "BUSINESS_ERROR_01026", 
            "���o�^�ڋq�G���[�B");

    /**
     * �\�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01027 = errorMgr.defineErrorInfo(
            1027,
            "BUSINESS_ERROR_01027", 
            "�\�����̒l���s���ł��B");

    /**
     * �K�p�J�n���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01028 = errorMgr.defineErrorInfo(
            1028,
            "BUSINESS_ERROR_01028", 
            "�K�p�J�n���̒l���s���ł��B");

    /**
     * �K�p�I�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01029 = errorMgr.defineErrorInfo(
            1029,
            "BUSINESS_ERROR_01029", 
            "�K�p�I�����̒l���s���ł��B");

    /**
     * ���p���������̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01030 = errorMgr.defineErrorInfo(
            1030,
            "BUSINESS_ERROR_01030", 
            "���p���������̓G���[�B");

    /**
     * �o���]�̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01031 = errorMgr.defineErrorInfo(
            1031,
            "BUSINESS_ERROR_01031", 
            "�o���]�̓G���[�B�i�o���]�͂͗��p������菬�����ł��j");

    /**
     * �o�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01032 = errorMgr.defineErrorInfo(
            1032,
            "BUSINESS_ERROR_01032", 
            "�o�����G���[�B");

    /**
     * �U�։\�񐔃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01033 = errorMgr.defineErrorInfo(
            1033,
            "BUSINESS_ERROR_01033", 
            "�����_�ł̏،��U�։񐔂��A�U�։\�񐔂𒴂��Ă��܂��B");

    /**
     * �ڋq���݃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01035 = errorMgr.defineErrorInfo(
            1035,
            "BUSINESS_ERROR_01035", 
            "�Y������ڋq�����݂��܂���B");

    /**
     * �\�������ݒ�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01036 = errorMgr.defineErrorInfo(
            1036,
            "BUSINESS_ERROR_01036", 
            "�\�������ݒ肪�o�^����Ă��Ȃ��B");

    /**
     * �Y���f�[�^�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01037 = errorMgr.defineErrorInfo(
            1037,
            "BUSINESS_ERROR_01037", 
            "�����ɊY������f�[�^�����݂��Ȃ��B");

    /**
     * �L��/�����敪�`�G�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01038 = errorMgr.defineErrorInfo(
            1038,
            "BUSINESS_ERROR_01038", 
            "�L���^�����敪���s�����B");

    /**
     * �\�����b�Z�[�W�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01039 = errorMgr.defineErrorInfo(
            1039,
            "BUSINESS_ERROR_01039", 
            "�\�����b�Z�[�W�����݂��Ȃ��B");

    /**
     * �\�����eID�`�G�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01040 = errorMgr.defineErrorInfo(
            1040,
            "BUSINESS_ERROR_01040", 
            "�\�����eID�����w��ł��B");

    /**
     * �L��/�����敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01041 = errorMgr.defineErrorInfo(
            1041,
            "BUSINESS_ERROR_01041", 
            "�L��/�����敪�����w��ł��B");

    /**
     * �ڋq�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01043 = errorMgr.defineErrorInfo(
            1043,
            "BUSINESS_ERROR_01043", 
            "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");

    /**
     * �\�������ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01044 = errorMgr.defineErrorInfo(
            1044,
            "BUSINESS_ERROR_01044", 
            "�\�������ԍ������w��ł��B");

    /**
     * �\���^�C�g���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01045 = errorMgr.defineErrorInfo(
            1045,
            "BUSINESS_ERROR_01045", 
            "�\���^�C�g���̓��͂��s���ł��B");

    /**
     * �\�����̓`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01046 = errorMgr.defineErrorInfo(
            1046,
            "BUSINESS_ERROR_01046", 
            "�\�����͂̓��͂��s���ł��B");

    /**
     * �\���}�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01047 = errorMgr.defineErrorInfo(
            1047,
            "BUSINESS_ERROR_01047", 
            "�\���}�̂����w��ł��B");

    /**
     * �\���D��x�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01048 = errorMgr.defineErrorInfo(
            1048,
            "BUSINESS_ERROR_01048", 
            "�\���D��x�`�F�b�N�G���[�B");

    /**
     * �\������(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01049 = errorMgr.defineErrorInfo(
            1049,
            "BUSINESS_ERROR_01049", 
            "�\������(��)�G���[�B(���݂��Ȃ����t���A���ݎ����ȑO�̓��t�����͂��ꂽ�B)");

    /**
     * �\������(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01050 = errorMgr.defineErrorInfo(
            1050,
            "BUSINESS_ERROR_01050", 
            "�\������(��)�G���[�B(���݂��Ȃ����t���A���ݎ����ȑO�̓��t�����͂��ꂽ�B)");

    /**
     * �\������(���`��)�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01051 = errorMgr.defineErrorInfo(
            1051,
            "BUSINESS_ERROR_01051", 
            "�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B");

    /**
     * �\���F�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01052 = errorMgr.defineErrorInfo(
            1052,
            "BUSINESS_ERROR_01052", 
            "�\���F�`�F�b�N�G���[�B");

    /**
     * URL�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01053 = errorMgr.defineErrorInfo(
            1053,
            "BUSINESS_ERROR_01053", 
            "URL�`�F�b�N�G���[�B");

    /**
     * �\�����b�Z�[�W�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01054 = errorMgr.defineErrorInfo(
            1054,
            "BUSINESS_ERROR_01054", 
            "�\�����b�Z�[�W�����������w��ł��B");

    /**
     * �ԍϒ������ʂ����݂̕ԍω\���ʐ��ʂ𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01055 = errorMgr.defineErrorInfo(
            1055,
            "BUSINESS_ERROR_01055", 
            "�ԍϒ������ʂ����݂̕ԍω\���ʐ��ʂ𒴂��Ă��܂��B");

    /**
     * �Ǘ��Ҍ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01056 = errorMgr.defineErrorInfo(
            1056,
            "BUSINESS_ERROR_01056", 
            "�Ǘ��Ҍ����`�F�b�N�G���[�B");

    /**
     * �����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01057 = errorMgr.defineErrorInfo(
            1057,
            "BUSINESS_ERROR_01057", 
            "�����G���[�B�i�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�Ɖ���j");

    /**
     * ���׍s�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01058 = errorMgr.defineErrorInfo(
            1058,
            "BUSINESS_ERROR_01058", 
            "�ڋq�t�@@�C�����e�G���[�B");

    /**
     * ����񍐏������{�ڋq�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01059 = errorMgr.defineErrorInfo(
            1059,
            "BUSINESS_ERROR_01059", 
            "����񍐏������{�ڋq�G���[�B");

    /**
     * ���׊Ǘ��ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01060 = errorMgr.defineErrorInfo(
            1060,
            "BUSINESS_ERROR_01060", 
            "���׊Ǘ��ԍ������w��ł��B");

    /**
     * �|��E�v���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01061 = errorMgr.defineErrorInfo(
            1061,
            "BUSINESS_ERROR_01061", 
            "�|��E�v�������w��ł��B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01062 = errorMgr.defineErrorInfo(
            1062,
            "BUSINESS_ERROR_01062", 
            "�����������w��ł��B");

    /**
     * �\������(��)���t�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01065 = errorMgr.defineErrorInfo(
            1065,
            "BUSINESS_ERROR_01065", 
            "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");

    /**
     * �\������(��)���t�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01066 = errorMgr.defineErrorInfo(
            1066,
            "BUSINESS_ERROR_01066", 
            "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");

    /**
     * �����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01067 = errorMgr.defineErrorInfo(
            1067,
            "BUSINESS_ERROR_01067", 
            "�����R�[�h�̓��͂��s���ł��B");

    /**
     * ���i�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01068 = errorMgr.defineErrorInfo(
            1068,
            "BUSINESS_ERROR_01068", 
            "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����Y����������݃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01070 = errorMgr.defineErrorInfo(
            1070,
            "BUSINESS_ERROR_01070", 
            "�����Y����������݃G���[�B");

    /**
     * ���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01071 = errorMgr.defineErrorInfo(
            1071,
            "BUSINESS_ERROR_01071", 
            "���ʂ����w��ł��B");

    /**
     * ���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01074 = errorMgr.defineErrorInfo(
            1074,
            "BUSINESS_ERROR_01074", 
            "�Ǘ��҂͊Y�����X�ɑ΂��錠�����Ȃ��ł��B");

    /**
     * �⑫���̓`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01075 = errorMgr.defineErrorInfo(
            1075,
            "BUSINESS_ERROR_01075", 
            "���I�̖����T�[�r�X�ł́A���p����ID�͕K�{�ł��B");

    /**
     * �⑫���̓`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01076 = errorMgr.defineErrorInfo(
            1076,
            "BUSINESS_ERROR_01076", 
            "���I�̖����T�[�r�X�ł́A���D�z�̓��͂͂ł��܂���B");

    /**
     * �⑫���̓`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01077 = errorMgr.defineErrorInfo(
            1077,
            "BUSINESS_ERROR_01077", 
            "���I���L��T�[�r�X�ł́A���p����ID�͓��͂ł��܂���B");

    /**
     * ��n���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01079 = errorMgr.defineErrorInfo(
            1079,
            "BUSINESS_ERROR_01079", 
            "��n�������w��ł��B");

    /**
     * �T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01080 = errorMgr.defineErrorInfo(
            1080,
            "BUSINESS_ERROR_01080", 
            "�T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s���擾�ł��܂���B");

    /**
     * ���������������݃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01081 = errorMgr.defineErrorInfo(
            1081,
            "BUSINESS_ERROR_01081", 
            "���������������݃G���[�B");

    /**
     * �\�����ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01082 = errorMgr.defineErrorInfo(
            1082,
            "BUSINESS_ERROR_01082", 
            "�\�����Ԃ����w��ł��B");

    /**
     * �\�����Ԃ�����`�̒l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01083 = errorMgr.defineErrorInfo(
            1083,
            "BUSINESS_ERROR_01083", 
            "�\�����Ԃ����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���i�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01084 = errorMgr.defineErrorInfo(
            1084,
            "BUSINESS_ERROR_01084", 
            "���i�R�[�h�����w��ł��B");

    /**
     * ���i�R�[�h������`�̒l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01085 = errorMgr.defineErrorInfo(
            1085,
            "BUSINESS_ERROR_01085", 
            "���i�R�[�h�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ����s��̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01087 = errorMgr.defineErrorInfo(
            1087,
            "BUSINESS_ERROR_01087", 
            "����s��̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �Ïؔԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01090 = errorMgr.defineErrorInfo(
            1090,
            "BUSINESS_ERROR_01090", 
            "�Ïؔԍ������w��ł��B");

    /**
     * ���O�C�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01091 = errorMgr.defineErrorInfo(
            1091,
            "BUSINESS_ERROR_01091", 
            "���O�C���������w��ł��B");

    /**
     * �w����ʂ̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01092 = errorMgr.defineErrorInfo(
            1092,
            "BUSINESS_ERROR_01092", 
            "�w����ʂ̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �V�Ïؔԍ��P�C�V�Ïؔԍ��Q�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01093 = errorMgr.defineErrorInfo(
            1093,
            "BUSINESS_ERROR_01093", 
            "�V�Ïؔԍ��P�����͐V�Ïؔԍ��Q�����w��ł��B");

    /**
     * �⏕�����͐敨�؋��������̏ꍇ�A�����������戵�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01094 = errorMgr.defineErrorInfo(
            1094,
            "BUSINESS_ERROR_01094", 
            "�⏕�����͐敨�؋��������̏ꍇ�A�����������戵�s�ł��B");

    /**
     * �萔���R�[�X�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01095 = errorMgr.defineErrorInfo(
            1095,
            "BUSINESS_ERROR_01095", 
            "�萔���R�[�X�����w��ł��B");

    /**
     * �萔���R�[�X�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01096 = errorMgr.defineErrorInfo(
            1096,
            "BUSINESS_ERROR_01096", 
            "�萔���R�[�X�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���Z�@@�֖��̂��S�p�������ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01097 = errorMgr.defineErrorInfo(
            1097,
            "BUSINESS_ERROR_01097", 
            "���Z�@@�֖��̂��S�p�����ł͂���܂���B");

    /**
     * ���Z�@@�֖��̂̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01098 = errorMgr.defineErrorInfo(
            1098,
            "BUSINESS_ERROR_01098", 
            "���Z�@@�֖��̂̃T�C�Y���s���ł��B");

    /**
     * �x�X�R�[�h�����p�������ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01099 = errorMgr.defineErrorInfo(
            1099,
            "BUSINESS_ERROR_01099", 
            "�x�X�R�[�h�����p�����ł͂���܂���B");

    /**
     * �x�X�R�[�h�̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01100 = errorMgr.defineErrorInfo(
            1100,
            "BUSINESS_ERROR_01100", 
            "�x�X�R�[�h�̃T�C�Y���s���ł��B");

    /**
     * �x�X���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01101 = errorMgr.defineErrorInfo(
            1101,
            "BUSINESS_ERROR_01101", 
            "�x�X�����S�p�����ł͂���܂���B");

    /**
     * �x�X���̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01102 = errorMgr.defineErrorInfo(
            1102,
            "BUSINESS_ERROR_01102", 
            "�x�X���̃T�C�Y���s���ł��B");

    /**
     * ������ޖ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01103 = errorMgr.defineErrorInfo(
            1103,
            "BUSINESS_ERROR_01103", 
            "������ޖ����S�p�����ł͂���܂���B");

    /**
     * ������ޖ��̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01104 = errorMgr.defineErrorInfo(
            1104,
            "BUSINESS_ERROR_01104", 
            "������ޖ��̃T�C�Y���s���ł��B");

    /**
     * �����ԍ��̃`�F�b�N �B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01105 = errorMgr.defineErrorInfo(
            1105,
            "BUSINESS_ERROR_01105", 
            "�����ԍ��̒l�������ȊO�̒l�ł��B");

    /**
     * �����ԍ��̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01106 = errorMgr.defineErrorInfo(
            1106,
            "BUSINESS_ERROR_01106", 
            "�����ԍ��̃T�C�Y���s���ł��B");

    /**
     * �������`�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01107 = errorMgr.defineErrorInfo(
            1107,
            "BUSINESS_ERROR_01107", 
            "�������`�l���S�p�����ł͂���܂���B");

    /**
     * �������`�l�̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01108 = errorMgr.defineErrorInfo(
            1108,
            "BUSINESS_ERROR_01108", 
            "�������`�l�̃T�C�Y���s���ł��B");

    /**
     * �����^�C�v�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01109 = errorMgr.defineErrorInfo(
            1109,
            "BUSINESS_ERROR_01109", 
            "�����^�C�v�敪�����w��ł��B");

    /**
     * �����^�C�v�敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01110 = errorMgr.defineErrorInfo(
            1110,
            "BUSINESS_ERROR_01110", 
            "�����^�C�v�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���^�����敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01111 = errorMgr.defineErrorInfo(
            1111,
            "BUSINESS_ERROR_01111", 
            "���^�����敪�����w��ł��B");

    /**
     * ���^�����敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01112 = errorMgr.defineErrorInfo(
            1112,
            "BUSINESS_ERROR_01112", 
            "���^�����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �g�єԍ������p�����ƃn�C�t�������i�f-�f�j���ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01113 = errorMgr.defineErrorInfo(
            1113,
            "BUSINESS_ERROR_01113", 
            "�g�єԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");

    /**
     * �Ζ��於�̂��S�p�������ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01115 = errorMgr.defineErrorInfo(
            1115,
            "BUSINESS_ERROR_01115", 
            "�Ζ��於�̂��S�p�����ł͂���܂���B");

    /**
     * �Ζ��於�̂̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01116 = errorMgr.defineErrorInfo(
            1116,
            "BUSINESS_ERROR_01116", 
            "�Ζ��於�̂̃T�C�Y���s���ł��B");

    /**
     * �Ζ���X�֔ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01117 = errorMgr.defineErrorInfo(
            1117,
            "BUSINESS_ERROR_01117", 
            "�Ζ���X�֔ԍ���7byte���p�����ł͂���܂���B");

    /**
     * �Ζ���Z�����S�p�������ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01119 = errorMgr.defineErrorInfo(
            1119,
            "BUSINESS_ERROR_01119", 
            "�Ζ���Z�����S�p�����ł͂���܂���B");

    /**
     * �Ζ���Z���̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01120 = errorMgr.defineErrorInfo(
            1120,
            "BUSINESS_ERROR_01120", 
            "�Ζ���Z���̃T�C�Y���s���ł��B");

    /**
     * �Ζ���d�b�ԍ������p�����ƃn�C�t�������i�f-�f�j���ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01121 = errorMgr.defineErrorInfo(
            1121,
            "BUSINESS_ERROR_01121", 
            "�Ζ���d�b�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");

    /**
     * ��E�����S�p�������ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01123 = errorMgr.defineErrorInfo(
            1123,
            "BUSINESS_ERROR_01123", 
            "��E�����S�p�����ł͂���܂���B");

    /**
     * ��E���̕������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01124 = errorMgr.defineErrorInfo(
            1124,
            "BUSINESS_ERROR_01124", 
            "��E���̃T�C�Y���s���ł��B");

    /**
     * �x�q�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01125 = errorMgr.defineErrorInfo(
            1125,
            "BUSINESS_ERROR_01125", 
            "�x�q�敪�����w��ł��B");

    /**
     * �x�q�敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01126 = errorMgr.defineErrorInfo(
            1126,
            "BUSINESS_ERROR_01126", 
            "�x�q�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �Ǘ����b�N�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01127 = errorMgr.defineErrorInfo(
            1127,
            "BUSINESS_ERROR_01127", 
            "�Ǘ����b�N�敪�����w��ł��B");

    /**
     * �Ǘ����b�N�敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01128 = errorMgr.defineErrorInfo(
            1128,
            "BUSINESS_ERROR_01128", 
            "�Ǘ����b�N�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �Ǘ����b�N�����J�n���^�Ǘ����b�N�����I�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01129 = errorMgr.defineErrorInfo(
            1129,
            "BUSINESS_ERROR_01129", 
            "�Ǘ����b�N�����J�n���ƊǗ����b�N�����I�����͂����ꂩ�����͂���鎞�A�c���������͂����͂��ł��B");

    /**
     * �Ǘ����b�N�����J�n���A�Ǘ����b�N�����I�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01130 = errorMgr.defineErrorInfo(
            1130,
            "BUSINESS_ERROR_01130", 
            "�Ǘ����b�N�����J�n���͊Ǘ����b�N�����I�������傫���ł��B");

    /**
     * �x�X���b�N�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01131 = errorMgr.defineErrorInfo(
            1131,
            "BUSINESS_ERROR_01131", 
            "�x�X���b�N�敪�����w��ł��B");

    /**
     * �x�X���b�N�敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01132 = errorMgr.defineErrorInfo(
            1132,
            "BUSINESS_ERROR_01132", 
            "�x�X���b�N�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����F�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01133 = errorMgr.defineErrorInfo(
            1133,
            "BUSINESS_ERROR_01133", 
            "�����F�敪�����w��ł��B");

    /**
     * �����F�敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01134 = errorMgr.defineErrorInfo(
            1134,
            "BUSINESS_ERROR_01134", 
            "�����F�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����F�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01135 = errorMgr.defineErrorInfo(
            1135,
            "BUSINESS_ERROR_01135", 
            "�F���s���ۂɂ́A���b�N���̏�ԂłȂ���΂Ȃ�܂���B�܂��F���̏�Ԃɂ���Ƃ��ɂ́A���b�N�����͂ł��܂���B");

    /**
     * �o�^�󋵋敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01137 = errorMgr.defineErrorInfo(
            1137,
            "BUSINESS_ERROR_01137", 
            "�o�^�󋵋敪�����w��ł��B");

    /**
     * �o�^�󋵋敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01138 = errorMgr.defineErrorInfo(
            1138,
            "BUSINESS_ERROR_01138", 
            "�o�^�󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �萔���m���D�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01141 = errorMgr.defineErrorInfo(
            1141,
            "BUSINESS_ERROR_01141", 
            "�萔���m���D�����w��ł��B");

    /**
     * �萔���m���D���������ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01142 = errorMgr.defineErrorInfo(
            1142,
            "BUSINESS_ERROR_01142", 
            "�萔���m���D�������ł͂���܂���B");

    /**
     * �萔���m���D�̃T�C�Y�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01143 = errorMgr.defineErrorInfo(
            1143,
            "BUSINESS_ERROR_01143", 
            "�萔���m���D�̃T�C�Y���s���ł��B");

    /**
     * �������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01144 = errorMgr.defineErrorInfo(
            1144,
            "BUSINESS_ERROR_01144", 
            "�����������w��ł��B");

    /**
     * ���������������ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01145 = errorMgr.defineErrorInfo(
            1145,
            "BUSINESS_ERROR_01145", 
            "�������������ł͂���܂���B");

    /**
     * �������̗L���l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01146 = errorMgr.defineErrorInfo(
            1146,
            "BUSINESS_ERROR_01146", 
            "�������̗L���l��0�\100�̊Ԃł͂���܂���B");

    /**
     * �J�n���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01149 = errorMgr.defineErrorInfo(
            1149,
            "BUSINESS_ERROR_01149", 
            "�J�n�������w��ł��B");

    /**
     * �I�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01150 = errorMgr.defineErrorInfo(
            1150,
            "BUSINESS_ERROR_01150", 
            "�I���������w��ł��B");

    /**
     * �J�n���A�I�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01151 = errorMgr.defineErrorInfo(
            1151,
            "BUSINESS_ERROR_01151", 
            "�J�n���͏I�������傫���ł��B");

    /**
     * �ύX�チ�[���A�h���X�A���[���A�h���X�폜�t���O�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01153 = errorMgr.defineErrorInfo(
            1153,
            "BUSINESS_ERROR_01153", 
            "���[���A�h���X�ύX�̏ꍇ�́A�ύX�チ�[���A�h���X�����w��ł��B");

    /**
     * �ύX�チ�[���A�h���X�A���[���A�h���X�폜�t���O�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01154 = errorMgr.defineErrorInfo(
            1154,
            "BUSINESS_ERROR_01154", 
            "���[���A�h���X�폜�̏ꍇ�́A�ύX�チ�[���A�h���X���w��s�ł��B");

    /**
     * ���茋�ʋ敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01157 = errorMgr.defineErrorInfo(
            1157,
            "BUSINESS_ERROR_01157", 
            "���茋�ʋ敪�����w��ł��B");

    /**
     * ���茋�ʋ敪�̃R�[�h�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01158 = errorMgr.defineErrorInfo(
            1158,
            "BUSINESS_ERROR_01158", 
            "���茋�ʋ敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����������ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01159 = errorMgr.defineErrorInfo(
            1159,
            "BUSINESS_ERROR_01159", 
            "�ڋq�R�[�h�C�J�n���C�I�����̂��ׂĂ������͂ł��B");

    /**
     * �����������ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01160 = errorMgr.defineErrorInfo(
            1160,
            "BUSINESS_ERROR_01160", 
            "�J�n���ƏI�����͂����ꂩ�����͂���鎞�A�c���������͂����͂��ł��B");

    /**
     * �ύX����A��p�U��������폜�t���O�̃`�F�b�N1�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01162 = errorMgr.defineErrorInfo(
            1162,
            "BUSINESS_ERROR_01162", 
            "��p�U��������ύX�̏ꍇ�́A�ύX���񂪖��w��ł��B");

    /**
     * �ύX����A��p�U��������폜�t���O�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01163 = errorMgr.defineErrorInfo(
            1163,
            "BUSINESS_ERROR_01163", 
            "��p�U��������폜�̏ꍇ�́A�ύX���񂪎w��s�ł��B");

    /**
     * ��~���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01164 = errorMgr.defineErrorInfo(
            1164,
            "BUSINESS_ERROR_01164", 
            "��~��񂪖��w��ł��B");

    /**
     * �����ҏ�񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01165 = errorMgr.defineErrorInfo(
            1165,
            "BUSINESS_ERROR_01165", 
            "�����ҏ�񂪖��w��ł��B");

    /**
     * �o�^�󋵂ɕύX������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01166 = errorMgr.defineErrorInfo(
            1166,
            "BUSINESS_ERROR_01166", 
            "�o�^�󋵂ɕύX������܂���B");

    /**
     * ����m�F���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01167 = errorMgr.defineErrorInfo(
            1167,
            "BUSINESS_ERROR_01167", 
            "����m�F���̏ꍇ�A�g�єԍ��E�Ζ�����ύX�\���s�B");

    /**
     * �ϑ��萔���R�[�X�}�X�^�e�[�u����茟���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01168 = errorMgr.defineErrorInfo(
            1168,
            "BUSINESS_ERROR_01168", 
            "�ϑ��萔���R�[�X�}�X�^�f�[�^���擾�ł��܂���B");

    /**
     * �g�єԍ��E�Ζ�����ύX�\�����f�[�^�s�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01169 = errorMgr.defineErrorInfo(
            1169,
            "BUSINESS_ERROR_01169", 
            "�g�єԍ��E�Ζ�����ύX�\�����f�[�^�s�����ł��B");

    /**
     * ��p�U��������t�@@�C�����e�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01170 = errorMgr.defineErrorInfo(
            1170,
            "BUSINESS_ERROR_01170", 
            "��p�U��������t�@@�C�����e�G���[�B");

    /**
     * �Ǘ����b�N�����J�n���F���ԓ��ł��邩�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01171 = errorMgr.defineErrorInfo(
            1171,
            "BUSINESS_ERROR_01171", 
            "�Ǘ����b�N�����J�n���͎w����Ԃɂ���܂���B");

    /**
     * �Ǘ����b�N�����J�n���F�c�Ɠ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01172 = errorMgr.defineErrorInfo(
            1172,
            "BUSINESS_ERROR_01172", 
            "�Ǘ����b�N�����J�n���͉c�Ɠ��ł͂���܂���B");

    /**
     * �Ǘ����b�N�����I�����F���ԓ��ł��邩�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01173 = errorMgr.defineErrorInfo(
            1173,
            "BUSINESS_ERROR_01173", 
            "�Ǘ����b�N�����I�����͎w����Ԃɂ���܂���B");

    /**
     * �Ǘ����b�N�����I�����F�c�Ɠ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01174 = errorMgr.defineErrorInfo(
            1174,
            "BUSINESS_ERROR_01174", 
            "�Ǘ����b�N�����I�����͉c�Ɠ��ł͂���܂���B");

    /**
     * �Ǘ����b�N�����J�n���^�I�����̊֘A�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01175 = errorMgr.defineErrorInfo(
            1175,
            "BUSINESS_ERROR_01175", 
            "�Ǘ����b�N�����J�n���ƊǗ����b�N�����I�����͌������ł��B");

    /**
     * ��~���F�ύX���ڊ����l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01176 = errorMgr.defineErrorInfo(
            1176,
            "BUSINESS_ERROR_01176", 
            "��~���ɂ́A�ύX���ڂ�����܂���B");

    /**
     * ���Y�ڋq�������\���\�̔���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01177 = errorMgr.defineErrorInfo(
            1177,
            "BUSINESS_ERROR_01177", 
            "���Y�ڋq�������\���s�B");

    /**
     * �����\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01178 = errorMgr.defineErrorInfo(
            1178,
            "BUSINESS_ERROR_01178", 
            "�����\���`�F�b�N�G���[�B�i�\����ʋ敪���h�����\���h�̏ꍇ�A�T�[�r�X�\���o�^�����̏ꍇ�j");

    /**
     * �\����ʋ敪�w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01179 = errorMgr.defineErrorInfo(
            1179,
            "BUSINESS_ERROR_01179", 
            "�\����ʋ敪�w��G���[�B");

    /**
     * �񋟌`���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01180 = errorMgr.defineErrorInfo(
            1180,
            "BUSINESS_ERROR_01180", 
            "�񋟌`�������݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �萔����������v�z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01181 = errorMgr.defineErrorInfo(
            1181,
            "BUSINESS_ERROR_01181", 
            "�萔����������v�z�����l�ȊO�̒l�ł��B");

    /**
     * �萔����������v�z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01182 = errorMgr.defineErrorInfo(
            1182,
            "BUSINESS_ERROR_01182", 
            "�T�[�r�X�̖����񋟏������w�肵���ꍇ�́A�萔�������̍��v�z�̓��͂��K�v�ł��B");

    /**
     * �萔����������v�z�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01183 = errorMgr.defineErrorInfo(
            1183,
            "BUSINESS_ERROR_01183", 
            "�T�[�r�X�̖����񋟏������w�肵���ꍇ�́A�萔�������̍��v�z��1�~�ȏ�̓��͂��K�v�ł��B");

    /**
     * �K�p�萔�������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01184 = errorMgr.defineErrorInfo(
            1184,
            "BUSINESS_ERROR_01184", 
            "�T�[�r�X�̖����񋟏������w�肵���ꍇ�́A�K�p����萔���̏����w�肪�K�v�ł��B");

    /**
     * �K�p�萔�������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01185 = errorMgr.defineErrorInfo(
            1185,
            "BUSINESS_ERROR_01185", 
            "�T�[�r�X�̖����񋟏������w�肵�Ȃ��ꍇ�́A�K�p����萔���̏����w��͂ł��܂���B");

    /**
     * �K�p�萔�������̏��i���ދ敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01186 = errorMgr.defineErrorInfo(
            1186,
            "BUSINESS_ERROR_01186", 
            "�K�p�萔�������̏��i���ދ敪�����w��ł��B");

    /**
     * �]�͎c���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01187 = errorMgr.defineErrorInfo(
            1187,
            "BUSINESS_ERROR_01187", 
            "�]�͎c���G���[�B");

    /**
     * �������͒l���Ó��ł��邩���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01188 = errorMgr.defineErrorInfo(
            1188,
            "BUSINESS_ERROR_01188", 
            "���̎s��ł͌��݁A���ʁE�����P���E���s�����̕������ڂ����������s�ł��B");

    /**
     * ���҃R�[�h�o�^�ς݃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01190 = errorMgr.defineErrorInfo(
            1190,
            "BUSINESS_ERROR_01190", 
            "�w��̈��҃R�[�h�����ɓo�^�ς݂ł��B");

    /**
     * ���҂����݂��Ȃ��ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01191 = errorMgr.defineErrorInfo(
            1191,
            "BUSINESS_ERROR_01191", 
            "���҂����݂��Ȃ��B");

    /**
     * �I�y���[�^�R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01192 = errorMgr.defineErrorInfo(
            1192,
            "BUSINESS_ERROR_01192", 
            "�I�y���[�^�R�[�h�����w��ł��B");

    /**
     * �I�y���[�^���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01193 = errorMgr.defineErrorInfo(
            1193,
            "BUSINESS_ERROR_01193", 
            "�I�y���[�^�������w��ł��B");

    /**
     * �I�y���[�^���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01194 = errorMgr.defineErrorInfo(
            1194,
            "BUSINESS_ERROR_01194", 
            "�I�y���[�^���̃T�C�Y���s���ł��B");

    /**
     * ��s�����\�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01195 = errorMgr.defineErrorInfo(
            1195,
            "BUSINESS_ERROR_01195", 
            "��s�����\�敪�����w��ł��B");

    /**
     * ��s�����\�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01196 = errorMgr.defineErrorInfo(
            1196,
            "BUSINESS_ERROR_01196", 
            "��s�����\�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01197 = errorMgr.defineErrorInfo(
            1197,
            "BUSINESS_ERROR_01197", 
            "�����R�[�h�������ȊO�̒l�ł��B");

    /**
     * �����R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01198 = errorMgr.defineErrorInfo(
            1198,
            "BUSINESS_ERROR_01198", 
            "�����R�[�h�̃T�C�Y���s���ł��B");

    /**
     * �������x���R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01201 = errorMgr.defineErrorInfo(
            1201,
            "BUSINESS_ERROR_01201", 
            "�������x���R�[�h�����w��ł��B");

    /**
     * �������x���R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01202 = errorMgr.defineErrorInfo(
            1202,
            "BUSINESS_ERROR_01202", 
            "�������x���R�[�h�̃T�C�Y���s���ł��B");

    /**
     * �������x���R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01203 = errorMgr.defineErrorInfo(
            1203,
            "BUSINESS_ERROR_01203", 
            "�������x���R�[�h�������ȊO�̒l�ł��B");

    /**
     * �������x�����̂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01204 = errorMgr.defineErrorInfo(
            1204,
            "BUSINESS_ERROR_01204", 
            "�������x�����̂����w��ł��B");

    /**
     * �������x�����̂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01205 = errorMgr.defineErrorInfo(
            1205,
            "BUSINESS_ERROR_01205", 
            "�������x�����̂̃T�C�Y���s���ł��B");

    /**
     * DIR�Ǘ��҃t���O�A�������x���R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01206 = errorMgr.defineErrorInfo(
            1206,
            "BUSINESS_ERROR_01206", 
            "DIR�Ǘ��҂̏ꍇ�́A�������x���R�[�h�́g9�h�Ŏn�܂镶����łȂ���΂Ȃ�Ȃ��ł��B");

    /**
     * DIR�Ǘ��҃t���O�A�������x���R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01207 = errorMgr.defineErrorInfo(
            1207,
            "BUSINESS_ERROR_01207", 
            "�ʏ�Ǘ��҂̏ꍇ�́A�g9�h�Ŏn�܂錠�����x���R�[�h�͎g�p�ł��Ȃ��B");

    /**
     * �c�h�q�Ǘ��҃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01208 = errorMgr.defineErrorInfo(
            1208,
            "BUSINESS_ERROR_01208", 
            "�ʏ�Ǘ��҂̏ꍇ�́A�g9�h�Ŏn�܂錠�����x���R�[�h�͎g�p�ł��Ȃ��ł��B");

    /**
     * �Ǘ��Җ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01209 = errorMgr.defineErrorInfo(
            1209,
            "BUSINESS_ERROR_01209", 
            "�Ǘ��Җ��̃T�C�Y���s���ł��B");

    /**
     * �G���[�񐔂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01211 = errorMgr.defineErrorInfo(
            1211,
            "BUSINESS_ERROR_01211", 
            "�G���[�񐔂������ȊO�̒l�ł��B");

    /**
     * �G���[�񐔂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01212 = errorMgr.defineErrorInfo(
            1212,
            "BUSINESS_ERROR_01212", 
            "�G���[�񐔂̗L���l��0�\9�͈͓̔��ł͂���܂���B");

    /**
     * �@@�\�J�e�S���R�[�h�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01214 = errorMgr.defineErrorInfo(
            1214,
            "BUSINESS_ERROR_01214", 
            "�@@�\�J�e�S���R�[�h�ꗗ�����w��ł��B");

    /**
     * �Ǘ��҃R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01215 = errorMgr.defineErrorInfo(
            1215,
            "BUSINESS_ERROR_01215", 
            "�Ǘ��҃R�[�h�����w��ł��B");

    /**
     * �Ǘ��҃^�C�v���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01218 = errorMgr.defineErrorInfo(
            1218,
            "BUSINESS_ERROR_01218", 
            "�Ǘ��҃^�C�v��񂪖��w��ł��B");

    /**
     * �Ǘ��Җ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01219 = errorMgr.defineErrorInfo(
            1219,
            "BUSINESS_ERROR_01219", 
            "�Ǘ��Җ������w��ł��B");

    /**
     * �@@�\�J�e�S���R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01220 = errorMgr.defineErrorInfo(
            1220,
            "BUSINESS_ERROR_01220", 
            "�@@�\�J�e�S���R�[�h�����w��ł��B");

    /**
     * �Ǘ��҃R�[�h�ɊY������Ǘ��҂��擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01222 = errorMgr.defineErrorInfo(
            1222,
            "BUSINESS_ERROR_01222", 
            "�Ǘ��҃R�[�h�ɊY������Ǘ��҂��擾�ł��܂���B");

    /**
     * �Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01223 = errorMgr.defineErrorInfo(
            1223,
            "BUSINESS_ERROR_01223", 
            "�Ώۃf�[�^��DIR�Ǘ��҂̏ꍇ�́A�Y�����鏈���s�B");

    /**
     * �ʏ�Ǘ��҂�DIR�Ǘ��҂�o�^�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01225 = errorMgr.defineErrorInfo(
            1225,
            "BUSINESS_ERROR_01225", 
            "�ʏ�Ǘ��҂�DIR�Ǘ��҂�o�^�ł��܂���B");

    /**
     * �Ǘ��҃^�C�v�f�[�^�����ɑ��݂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01226 = errorMgr.defineErrorInfo(
            1226,
            "BUSINESS_ERROR_01226", 
            "�Ǘ��҃^�C�v�f�[�^�����ɑ��݂���B");

    /**
     * ���O�C�����̊Ǘ��҂̏ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01227 = errorMgr.defineErrorInfo(
            1227,
            "BUSINESS_ERROR_01227", 
            "�Ǘ��҂͎������g�ɑ΂���ύX�A�܂��͍폜���s�ł��B");

    /**
     * �Ǘ��҃R�[�h�����ɑ��݂���ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01229 = errorMgr.defineErrorInfo(
            1229,
            "BUSINESS_ERROR_01229", 
            "�w��̊Ǘ��҃R�[�h�����ɓo�^�ς݂ł��B");

    /**
     * �w����@@�i���t�j�G���[�i���M���������o�^�R���j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01231 = errorMgr.defineErrorInfo(
            1231,
            "BUSINESS_ERROR_01231", 
            "�w����@@�i���t�j�G���[�i���M���������o�^�R���j�B");

    /**
     * �w����@@�i���j�G���[�i���M���������o�^�R���j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01232 = errorMgr.defineErrorInfo(
            1232,
            "BUSINESS_ERROR_01232", 
            "�w����@@�i���j�G���[�i���M���������o�^�R���j�B");

    /**
     * �w����@@�i�抷�j�G���[�i���M���������o�^�R���j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01233 = errorMgr.defineErrorInfo(
            1233,
            "BUSINESS_ERROR_01233", 
            "�w����@@�i�抷�j�G���[�i���M���������o�^�R���j�B");

    /**
     * �����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01234 = errorMgr.defineErrorInfo(
            1234,
            "BUSINESS_ERROR_01234", 
            "�u���s�\�v����������Ă��܂��B");

    /**
     * �����X�V��񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01235 = errorMgr.defineErrorInfo(
            1235,
            "BUSINESS_ERROR_01235", 
            "�����X�V��񂪖��w��ł��B");

    /**
     * �����X�V���̗v�f�����O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01236 = errorMgr.defineErrorInfo(
            1236,
            "BUSINESS_ERROR_01236", 
            "�����X�V���̗v�f�����O�ł��B");

    /**
     * �����X�V���̖����R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01237 = errorMgr.defineErrorInfo(
            1237,
            "BUSINESS_ERROR_01237", 
            "�����X�V���̖����R�[�h�����w��ł��B");

    /**
     * �����X�V���̔��t�\�敪�i�����j�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01238 = errorMgr.defineErrorInfo(
            1238,
            "BUSINESS_ERROR_01238", 
            "�����X�V���̔��t�\�敪�i�����j�����w��ł��B");

    /**
     * �����X�V���̉��\�敪�i�����j�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01239 = errorMgr.defineErrorInfo(
            1239,
            "BUSINESS_ERROR_01239", 
            "�����X�V���̉��\�敪�i�����j�����w��ł��B");

    /**
     * �����X�V���̔��t�J�n���͖����X�V���̔��t�I�����ȏ�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01240 = errorMgr.defineErrorInfo(
            1240,
            "BUSINESS_ERROR_01240", 
            "�����X�V���̔��t�J�n���͖����X�V���̔��t�I�����ȏ�ł��B");

    /**
     * �����X�V���̉��J�n���͖����X�V���̉��I�����ȏ�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01241 = errorMgr.defineErrorInfo(
            1241,
            "BUSINESS_ERROR_01241", 
            "�����X�V���̉��J�n���͖����X�V���̉��I�����ȏ�ł��B");

    /**
     * �I�y���[�V�������t�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01242 = errorMgr.defineErrorInfo(
            1242,
            "BUSINESS_ERROR_01242", 
            "�I�y���[�V�������t�����w��ł��B");

    /**
     * ���M�����J�e�S���[�R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01243 = errorMgr.defineErrorInfo(
            1243,
            "BUSINESS_ERROR_01243", 
            "���M�����J�e�S���[�R�[�h�����w��ł��B");

    /**
     * ���M�����J�e�S���[�R�[�h�̌������������z���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01244 = errorMgr.defineErrorInfo(
            1244,
            "BUSINESS_ERROR_01244", 
            "���M�����J�e�S���[�R�[�h�̌������������z���Ă��܂��B");

    /**
     * ���M�����J�e�S���[���̂����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01245 = errorMgr.defineErrorInfo(
            1245,
            "BUSINESS_ERROR_01245", 
            "���M�����J�e�S���[���̂����w��ł��B");

    /**
     * ���M�����J�e�S���[���̒l�ɔ��p�J�i�������܂܂�Ă���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01246 = errorMgr.defineErrorInfo(
            1246,
            "BUSINESS_ERROR_01246", 
            "���M�����J�e�S���[���̒l�ɔ��p�J�i�������܂܂�Ă��܂��B");

    /**
     * ���M�����J�e�S���[���̂̒l���������z���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01247 = errorMgr.defineErrorInfo(
            1247,
            "BUSINESS_ERROR_01247", 
            "���M�����J�e�S���[���̂̒l���������z���Ă��܂��B");

    /**
     * �e�J�e�S���[�R�[�h�̌������������z���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01248 = errorMgr.defineErrorInfo(
            1248,
            "BUSINESS_ERROR_01248", 
            "�e�J�e�S���[�R�[�h�̌������������z���Ă��܂��B");

    /**
     * �����敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01249 = errorMgr.defineErrorInfo(
            1249,
            "BUSINESS_ERROR_01249", 
            "�����敪�����w��ł��B");

    /**
     * �����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01250 = errorMgr.defineErrorInfo(
            1250,
            "BUSINESS_ERROR_01250", 
            "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ������񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01251 = errorMgr.defineErrorInfo(
            1251,
            "BUSINESS_ERROR_01251", 
            "������񂪖��w��ł��B");

    /**
     * �����R�[�h�̎w�肪����܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01252 = errorMgr.defineErrorInfo(
            1252,
            "BUSINESS_ERROR_01252", 
            "�����R�[�h�̎w�肪����܂���B");

    /**
     * �������i�p���j�̒l�ɑS�p�������܂܂�Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01253 = errorMgr.defineErrorInfo(
            1253,
            "BUSINESS_ERROR_01253", 
            "�������i�p���j�̒l�ɑS�p�������܂܂�Ă��܂��B");

    /**
     * �w����@@�i���t�j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01254 = errorMgr.defineErrorInfo(
            1254,
            "BUSINESS_ERROR_01254", 
            "�w����@@�i���t�j�̎w��Ɍ�肪����܂��B");

    /**
     * �w����@@�i���j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01255 = errorMgr.defineErrorInfo(
            1255,
            "BUSINESS_ERROR_01255", 
            "�w����@@�i���j�̎w��Ɍ�肪����܂��B");

    /**
     * �w����@@�i�抷�j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01256 = errorMgr.defineErrorInfo(
            1256,
            "BUSINESS_ERROR_01256", 
            "�w����@@�i�抷�j�̎w��Ɍ�肪����܂��B");

    /**
     * �������̑S�Ă̑��������w��ł��B�i�����R�[�h�ȊO�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01257 = errorMgr.defineErrorInfo(
            1257,
            "BUSINESS_ERROR_01257", 
            "�������̑S�Ă̑��������w��ł��B�i�����R�[�h�ȊO�j");

    /**
     * ���t�\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01258 = errorMgr.defineErrorInfo(
            1258,
            "BUSINESS_ERROR_01258", 
            "���t�\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * ���\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01259 = errorMgr.defineErrorInfo(
            1259,
            "BUSINESS_ERROR_01259", 
            "���\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * �抷�\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01260 = errorMgr.defineErrorInfo(
            1260,
            "BUSINESS_ERROR_01260", 
            "�抷�\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * ���t�\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01261 = errorMgr.defineErrorInfo(
            1261,
            "BUSINESS_ERROR_01261", 
            "���t�\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * ���\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01262 = errorMgr.defineErrorInfo(
            1262,
            "BUSINESS_ERROR_01262", 
            "���\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * �抷�\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01263 = errorMgr.defineErrorInfo(
            1263,
            "BUSINESS_ERROR_01263", 
            "�抷�\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * ������t��~�J�n���ԁi�����j�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01264 = errorMgr.defineErrorInfo(
            1264,
            "BUSINESS_ERROR_01264", 
            "������t��~�J�n���ԁi�����j�������ȊO�̒l�ł��B");

    /**
     * ������t��~�I�����ԁi�����j�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01265 = errorMgr.defineErrorInfo(
            1265,
            "BUSINESS_ERROR_01265", 
            "������t��~�I�����ԁi�����j�������ȊO�̒l�ł��B");

    /**
     * ������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01266 = errorMgr.defineErrorInfo(
            1266,
            "BUSINESS_ERROR_01266", 
            "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");

    /**
     * ������t��~�I�����ԁi�����j�̓��͒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01267 = errorMgr.defineErrorInfo(
            1267,
            "BUSINESS_ERROR_01267", 
            "������t��~�I�����ԁi�����j�̓��͒l���s���ł��B");

    /**
     * ������t��~�J�n���ԁi�����j�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01268 = errorMgr.defineErrorInfo(
            1268,
            "BUSINESS_ERROR_01268", 
            "������t��~�J�n���ԁi�����j�������ȊO�̒l�ł��B");

    /**
     * ������t��~�I�����ԁi�����j�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01269 = errorMgr.defineErrorInfo(
            1269,
            "BUSINESS_ERROR_01269", 
            "������t��~�I�����ԁi�����j�������ȊO�̒l�ł��B");

    /**
     * ������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01270 = errorMgr.defineErrorInfo(
            1270,
            "BUSINESS_ERROR_01270", 
            "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");

    /**
     * ������t��~�I�����ԁi�����j�̓��͒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01271 = errorMgr.defineErrorInfo(
            1271,
            "BUSINESS_ERROR_01271", 
            "������t��~�I�����ԁi�����j�̓��͒l���s���ł��B");

    /**
     * �I�y���[�V�������t���w�肳��Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01272 = errorMgr.defineErrorInfo(
            1272,
            "BUSINESS_ERROR_01272", 
            "�I�y���[�V�������t���w�肳��Ă��܂���B");

    /**
     * �����\�������X�V�l�ꗗ�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01273 = errorMgr.defineErrorInfo(
            1273,
            "BUSINESS_ERROR_01273", 
            "�����\�������X�V�l�ꗗ�����w��ł��B");

    /**
     * �����\�������X�V�l�ꗗ�̗v�f�����O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01274 = errorMgr.defineErrorInfo(
            1274,
            "BUSINESS_ERROR_01274", 
            "�����\�������X�V�l�ꗗ�̗v�f�����O�ł��B");

    /**
     * �����\�������X�V�l�̕\���������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01275 = errorMgr.defineErrorInfo(
            1275,
            "BUSINESS_ERROR_01275", 
            "�����\�������X�V�l�̕\���������w��ł��B");

    /**
     * �����\�������X�V�l�̕\���������l�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01276 = errorMgr.defineErrorInfo(
            1276,
            "BUSINESS_ERROR_01276", 
            "�����\�������X�V�l�̕\���������l�ȊO�̒l�ł��B");

    /**
     * �����\�������X�V�l�̖����R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01277 = errorMgr.defineErrorInfo(
            1277,
            "BUSINESS_ERROR_01277", 
            "�����\�������X�V�l�̖����R�[�h�����w��ł��B");

    /**
     * �J�e�S���[�R�[�h�d���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01278 = errorMgr.defineErrorInfo(
            1278,
            "BUSINESS_ERROR_01278", 
            "�J�e�S���[�R�[�h�d���G���[�B");

    /**
     * �������ʂȂ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01279 = errorMgr.defineErrorInfo(
            1279,
            "BUSINESS_ERROR_01279", 
            "�������ʂȂ��B");

    /**
     * �폜�ƌ����J�݊������[���͓����Ɏw��ł��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01280 = errorMgr.defineErrorInfo(
            1280,
            "BUSINESS_ERROR_01280", 
            "�폜�ƌ����J�݊������[���͓����Ɏw��ł��Ȃ��B");

    /**
     * �e�J�e�S���[�R�[�h�Ɠ��M�����J�e�S���[�R�[�h�͓����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01281 = errorMgr.defineErrorInfo(
            1281,
            "BUSINESS_ERROR_01281", 
            "�e�J�e�S���[�R�[�h�Ɠ��M�����J�e�S���[�R�[�h�͓����ł��B");

    /**
     * �o�^���̊Ǘ��҂��g�p���Ă���Ǘ��҃^�C�v�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01282 = errorMgr.defineErrorInfo(
            1282,
            "BUSINESS_ERROR_01282", 
            "�o�^���̊Ǘ��҂��g�p���Ă���Ǘ��҃^�C�v�ł��B");

    /**
     * �萔���ύX�\���ڋq�_�E�����[�h�@@�\����舵���Ă��Ȃ��،���ЁB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01283 = errorMgr.defineErrorInfo(
            1283,
            "BUSINESS_ERROR_01283", 
            "�萔���ύX�\���ڋq�_�E�����[�h�@@�\����舵���Ă��Ȃ��،���ЁB");

    /**
     * DIR�Ǘ��҃t���O���ύX����Ă���ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01284 = errorMgr.defineErrorInfo(
            1284,
            "BUSINESS_ERROR_01284", 
            "DIR�Ǘ��҃t���O�͕ύX�ł��Ȃ��B");

    /**
     * ���O�C�����Ǘ��҂̌������x�����w�肳��Ă���ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01285 = errorMgr.defineErrorInfo(
            1285,
            "BUSINESS_ERROR_01285", 
            "�I�y���[�^�̌������x���͕ύX�ł��Ȃ��B");

    /**
     * ���͂��ꂽ�Ǘ��҃^�C�v��DIR�Ǘ��҂̏ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01286 = errorMgr.defineErrorInfo(
            1286,
            "BUSINESS_ERROR_01286", 
            "�ʏ�Ǘ��҂́ADIR�Ǘ��҂�ύX�ł��Ȃ��B");

    /**
     * ���p���ԁA���p�����������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01287 = errorMgr.defineErrorInfo(
            1287,
            "BUSINESS_ERROR_01287", 
            "���p���ԁA���p�����������͂ł��B");

    /**
     * ���p���ԁA���p�����̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01288 = errorMgr.defineErrorInfo(
            1288,
            "BUSINESS_ERROR_01288", 
            "���p���ԁA���p�����̎w��Ɍ�肪����܂��B");

    /**
     * �\�����ԓ��̏��̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01289 = errorMgr.defineErrorInfo(
            1289,
            "BUSINESS_ERROR_01289", 
            "�\�����ԓ��̏��̎w��Ɍ�肪����܂��B");

    /**
     * �X�e�[�^�X���u��~���v�ł͂Ȃ��A�����I�敪���u�L�v�̏ꍇ�A��W���ԏ��̑S�Ă̖����敪���u�����v�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01290 = errorMgr.defineErrorInfo(
            1290,
            "BUSINESS_ERROR_01290", 
            "�X�e�[�^�X���u��~���v�ł͂Ȃ��A�����I�敪���u�L�v�̏ꍇ�A��W���ԏ��̑S�Ă̖����敪���u�����v�ł��B");

    /**
     * ���ʃR�[�h�v���t�B�N�X�ꗗ�̎w��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01291 = errorMgr.defineErrorInfo(
            1291,
            "BUSINESS_ERROR_01291", 
            "���ʃR�[�h�v���t�B�N�X�ꗗ�̎w��Ȃ��B");

    /**
     * WEBBROKER3�戵�󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01292 = errorMgr.defineErrorInfo(
            1292,
            "BUSINESS_ERROR_01292", 
            "WEBBROKER3�戵�󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �ő�K�w�I�[�o�[�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01293 = errorMgr.defineErrorInfo(
            1293,
            "BUSINESS_ERROR_01293", 
            "�ő�K�w�I�[�o�[�G���[�B");

    /**
     * �؋����������J�݁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01294 = errorMgr.defineErrorInfo(
            1294,
            "BUSINESS_ERROR_01294", 
            "�؋����������J�݁B");

    /**
     * ���i�^�C�v�̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01295 = errorMgr.defineErrorInfo(
            1295,
            "BUSINESS_ERROR_01295", 
            "���i�^�C�v�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �a��敪�̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01297 = errorMgr.defineErrorInfo(
            1297,
            "BUSINESS_ERROR_01297", 
            "�a��敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �U�֐��ʂ����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01298 = errorMgr.defineErrorInfo(
            1298,
            "BUSINESS_ERROR_01298", 
            "�U�֐��ʂ����w��ł��B");

    /**
     * �U�֐��ʂ̒l��0�ȉ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01299 = errorMgr.defineErrorInfo(
            1299,
            "BUSINESS_ERROR_01299", 
            "�U�֐��ʂ̒l��0�ȉ��ł��B");

    /**
     * �U�֐��ʂ̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01300 = errorMgr.defineErrorInfo(
            1300,
            "BUSINESS_ERROR_01300", 
            "�U�֐��ʂ̃T�C�Y���s���ł��B");

    /**
     * �U�֐��ʂ������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01301 = errorMgr.defineErrorInfo(
            1301,
            "BUSINESS_ERROR_01301", 
            "�U�֐��ʂ������ȊO�̒l�ł��B");

    /**
     * �Ïؔԍ��̒l�ɑS�p�������܂܂�Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01302 = errorMgr.defineErrorInfo(
            1302,
            "BUSINESS_ERROR_01302", 
            "�Ïؔԍ��̒l�ɑS�p�������܂܂�Ă��܂��B");

    /**
     * �����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01303 = errorMgr.defineErrorInfo(
            1303,
            "BUSINESS_ERROR_01303", 
            "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �U�֐��ʂ́A�U�։\���ʂ��傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01305 = errorMgr.defineErrorInfo(
            1305,
            "BUSINESS_ERROR_01305", 
            "�U�֐��ʂ́A�U�։\���ʂ��傫���ł��B");

    /**
     * ����]�̓`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01306 = errorMgr.defineErrorInfo(
            1306,
            "BUSINESS_ERROR_01306", 
            "����]�̓`�F�b�N�G���[�B");

    /**
     * �����������.���敪�`�F�b�N�E�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01307 = errorMgr.defineErrorInfo(
            1307,
            "BUSINESS_ERROR_01307", 
            "���Y�����̏��敪���u����v�ł��B");

    /**
     * �K�{���ڂ̒l�����͂���Ă��܂���B�ǉ�������F[���ږ�]�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01309 = errorMgr.defineErrorInfo(
            1309,
            "BUSINESS_ERROR_01309", 
            "�K�{���ڂ̒l�����͂���Ă��܂���B");

    /**
     * ���ڒ������L���Ȕ͈͓��ł͂���܂���B�ǉ�������F[���ږ�]�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01310 = errorMgr.defineErrorInfo(
            1310,
            "BUSINESS_ERROR_01310", 
            "���ڒ������L���Ȕ͈͓��ł͂���܂���B");

    /**
     * �`�F�b�N�Ώۍ��ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B�ǉ�������F[���ږ�]�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01311 = errorMgr.defineErrorInfo(
            1311,
            "BUSINESS_ERROR_01311", 
            "�`�F�b�N�Ώۍ��ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���ڂ��L���Ȓl�ł͂���܂���B�ǉ�������F[���ږ�]�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01312 = errorMgr.defineErrorInfo(
            1312,
            "BUSINESS_ERROR_01312", 
            "���ڂ��L���Ȓl�ł͂���܂���B");

    /**
     * �ڋq���d���œo�^����Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01313 = errorMgr.defineErrorInfo(
            1313,
            "BUSINESS_ERROR_01313", 
            "�ڋq���d���œo�^����Ă��܂��B");

    /**
     * ���Z�@@�ւ����݂��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01314 = errorMgr.defineErrorInfo(
            1314,
            "BUSINESS_ERROR_01314", 
            "���Z�@@�ւ����݂��Ȃ��B");

    /**
     * �����J�݌����q���X�V�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01315 = errorMgr.defineErrorInfo(
            1315,
            "BUSINESS_ERROR_01315", 
            "�����J�݌����q���X�V�s�ł��B");

    /**
     * �����J�݌����q���`�[�쐬�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01316 = errorMgr.defineErrorInfo(
            1316,
            "BUSINESS_ERROR_01316", 
            "�����J�݌����q���`�[�쐬�s�ł��B");

    /**
     * �s�����ڂ��������ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01317 = errorMgr.defineErrorInfo(
            1317,
            "BUSINESS_ERROR_01317", 
            "�s�����ڂ��������ł��B");

    /**
     * �����J�݌����q�����f�[�^�����݂��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01318 = errorMgr.defineErrorInfo(
            1318,
            "BUSINESS_ERROR_01318", 
            "�����J�݌����q�����f�[�^�����݂��Ȃ��B");

    /**
     * �����J�݌����q������s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01319 = errorMgr.defineErrorInfo(
            1319,
            "BUSINESS_ERROR_01319", 
            "�����J�݌����q������s�ł��B");

    /**
     * �Ɖ�̃X�e�[�^�X�ƌ��݂̃X�e�[�^�X���s��v�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01320 = errorMgr.defineErrorInfo(
            1320,
            "BUSINESS_ERROR_01320", 
            "�Ɖ�̃X�e�[�^�X�ƌ��݂̃X�e�[�^�X���s��v�ł��B");

    /**
     * �Y���s�����݂��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01321 = errorMgr.defineErrorInfo(
            1321,
            "BUSINESS_ERROR_01321", 
            "�Y���s�����݂��Ȃ��B");

    /**
     * ���X�R�[�h�z��ƌڋq�R�[�h�z��ŁA�v�f��������ē���Ă܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01322 = errorMgr.defineErrorInfo(
            1322,
            "BUSINESS_ERROR_01322", 
            "���X�R�[�h�z��ƌڋq�R�[�h�z��ŁA�v�f��������ē���Ă܂��B");

    /**
     * �\���s�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01323 = errorMgr.defineErrorInfo(
            1323,
            "BUSINESS_ERROR_01323", 
            "�\���s�������w��ł��B");

    /**
     * �\���s���̒l�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01324 = errorMgr.defineErrorInfo(
            1324,
            "BUSINESS_ERROR_01324", 
            "�\���s���̒l�������ȊO�̒l�ł��B");

    /**
     * �\���s���̒l���}�C�i�X�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01325 = errorMgr.defineErrorInfo(
            1325,
            "BUSINESS_ERROR_01325", 
            "�\���s���̒l���}�C�i�X�l�ł��B");

    /**
     * �����������i���j�͎����������i���j���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01326 = errorMgr.defineErrorInfo(
            1326,
            "BUSINESS_ERROR_01326", 
            "�����������i���j�͎����������i���j���傫���ł��B");

    /**
     * SONAR���M���i���j��SONAR���M���i���j���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01327 = errorMgr.defineErrorInfo(
            1327,
            "BUSINESS_ERROR_01327", 
            "SONAR���M���i���j��SONAR���M���i���j���傫���ł��B");

    /**
     * �����J�ݓ��i���j�͌����J�ݓ��i���j���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01328 = errorMgr.defineErrorInfo(
            1328,
            "BUSINESS_ERROR_01328", 
            "�����J�ݓ��i���j�͌����J�ݓ��i���j���傫���ł��B");

    /**
     * �ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01330 = errorMgr.defineErrorInfo(
            1330,
            "BUSINESS_ERROR_01330", 
            "�ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B");

    /**
     * �ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01331 = errorMgr.defineErrorInfo(
            1331,
            "BUSINESS_ERROR_01331", 
            "�ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B");

    /**
     * ���ʃR�[�h�C�ڋq�R�[�h�̗����������͂̏ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01332 = errorMgr.defineErrorInfo(
            1332,
            "BUSINESS_ERROR_01332", 
            "���ʃR�[�h�A�ڋq�R�[�h�̗��������w��ł��B");

    /**
     * ���ʃR�[�h�i���j�͎��ʃR�[�h�i���j���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01333 = errorMgr.defineErrorInfo(
            1333,
            "BUSINESS_ERROR_01333", 
            "���ʃR�[�h�i���j�͎��ʃR�[�h�i���j���傫���ł��B");

    /**
     * ���ʃR�[�h�i���j�̒l�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01334 = errorMgr.defineErrorInfo(
            1334,
            "BUSINESS_ERROR_01334", 
            "���ʃR�[�h�i���j�̒l�������ȊO�̒l�ł��B");

    /**
     * ���ʃR�[�h�i���j�̒l�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01335 = errorMgr.defineErrorInfo(
            1335,
            "BUSINESS_ERROR_01335", 
            "���ʃR�[�h�i���j�̒l�������ȊO�̒l�ł��B");

    /**
     * �����J�ݐ\����񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01336 = errorMgr.defineErrorInfo(
            1336,
            "BUSINESS_ERROR_01336", 
            "�����J�ݐ\����񂪖��w��ł��B");

    /**
     * �`�[�쐬��񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01337 = errorMgr.defineErrorInfo(
            1337,
            "BUSINESS_ERROR_01337", 
            "�`�[�쐬��񂪖��w��ł��B");

    /**
     * �����J�ݐ\�����̏،���ЃR�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01338 = errorMgr.defineErrorInfo(
            1338,
            "BUSINESS_ERROR_01338", 
            "�����J�ݐ\�����̏،���ЃR�[�h�����w��ł��B");

    /**
     * �����J�ݐ\�����̕��X�R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01339 = errorMgr.defineErrorInfo(
            1339,
            "BUSINESS_ERROR_01339", 
            "�����J�ݐ\�����̕��X�R�[�h�����w��ł��B");

    /**
     * �l�i�����t�������E�l�i���m��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01340 = errorMgr.defineErrorInfo(
            1340,
            "BUSINESS_ERROR_01340", 
            "�l�i�����t�������E�l�i���m��B");

    /**
     * ���Y�ڋq�͊O���،������J�݂Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01341 = errorMgr.defineErrorInfo(
            1341,
            "BUSINESS_ERROR_01341", 
            "���Y�ڋq�͊O���،������J�݂Ȃ��B");

    /**
     * �l�i�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01342 = errorMgr.defineErrorInfo(
            1342,
            "BUSINESS_ERROR_01342", 
            "�戵�\�Ȓl�i�����ł͂���܂���B");

    /**
     * �l�i������NULL�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01343 = errorMgr.defineErrorInfo(
            1343,
            "BUSINESS_ERROR_01343", 
            "�l�i���������w��ł��B");

    /**
     * �l�i�����̒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01344 = errorMgr.defineErrorInfo(
            1344,
            "BUSINESS_ERROR_01344", 
            "�l�i����������`�̒l�ł��B");

    /**
     * ���s�w��`�F�b�N�i�󔄂�K�����ʒ��ߎ��j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01345 = errorMgr.defineErrorInfo(
            1345,
            "BUSINESS_ERROR_01345", 
            "���s�w��s�i�󔄂�K�����ʒ��ߎ��j�B");

    /**
     * ���s�����`�F�b�N�i�󔄂�K�����ʒ��ߎ��j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01346 = errorMgr.defineErrorInfo(
            1346,
            "BUSINESS_ERROR_01346", 
            "���s�����Ɂh�s�o���������s�h�w��s�i�󔄂�K�����ʒ��ߎ��j�B");

    /**
     * �l�i�����`�F�b�N�i�󔄂�K�����ʒ��ߎ��j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01347 = errorMgr.defineErrorInfo(
            1347,
            "BUSINESS_ERROR_01347", 
            "�l�i�����Ɂh�D��l�w�l�h�A�h���s�c���w�l�h�A�h���s�c������h�w��s�i�󔄂�K�����ʒ��ߎ��j�B");

    /**
     * �l�i�����E�����P���敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01348 = errorMgr.defineErrorInfo(
            1348,
            "BUSINESS_ERROR_01348", 
            "�l�i�������w��Ȃ��ȊO�̎��́A�����P���敪�𐬍s�ɐݒ肵�ĉ������B");

    /**
     * �l�i�����E���s�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01349 = errorMgr.defineErrorInfo(
            1349,
            "BUSINESS_ERROR_01349", 
            "�l�i���������s�c���w�l�������́A���s�c����������̎��́A���s�����𖳏�����ݒ肵�ĉ������B");

    /**
     * �l�i�����E���������敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01350 = errorMgr.defineErrorInfo(
            1350,
            "BUSINESS_ERROR_01350", 
            "�l�i�������w��Ȃ��ȊO�̎��́A���������敪�𓖓�����ɐݒ肵�ĉ������B");

    /**
     * �l�i�����E���������敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01351 = errorMgr.defineErrorInfo(
            1351,
            "BUSINESS_ERROR_01351", 
            "�l�i�������w��Ȃ��ȊO�̎��́A���������敪���w��Ȃ��ɐݒ肵�ĉ������B");

    /**
     * MM�������s�w��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01352 = errorMgr.defineErrorInfo(
            1352,
            "BUSINESS_ERROR_01352", 
            "MM�����͐��s�w��s�B");

    /**
     * �I�y���[�V�������t�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01353 = errorMgr.defineErrorInfo(
            1353,
            "BUSINESS_ERROR_01353", 
            "�I�y���[�V�������t�̎w��Ɍ�肪����܂��B");

    /**
     * �I�y���[�V�������t�����ݓ��t�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01354 = errorMgr.defineErrorInfo(
            1354,
            "BUSINESS_ERROR_01354", 
            "�I�y���[�V�������t�����ݓ��t�ł͂���܂���B");

    /**
     * ���s�w��K���`�F�b�N�i���������E���s�w��K�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01355 = errorMgr.defineErrorInfo(
            1355,
            "BUSINESS_ERROR_01355", 
            "���������E���s�w��K�����B");

    /**
     * �C���T�C�_�[�`�F�b�N�̂��ߎ����~���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01356 = errorMgr.defineErrorInfo(
            1356,
            "BUSINESS_ERROR_01356", 
            "�C���T�C�_�[�`�F�b�N�̂��ߎ����~���ł��B");

    /**
     * �ڋq�͎w������̊Y�������~���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01357 = errorMgr.defineErrorInfo(
            1357,
            "BUSINESS_ERROR_01357", 
            "�ڋq�͎w������̊Y�������~���B");

    /**
     * ���������̃`�F�b�N�R�i�h2�FW�w�l�h�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01358 = errorMgr.defineErrorInfo(
            1358,
            "BUSINESS_ERROR_01358", 
            "���������敪���hW�w�l�h�̏ꍇ�́AW�w�l�p�����P���敪���`���K�v�ł��B");

    /**
     * ���������̃`�F�b�N�R�i�h2�FW�w�l�h�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01359 = errorMgr.defineErrorInfo(
            1359,
            "BUSINESS_ERROR_01359", 
            "���������敪���hW�w�l�h�̏ꍇ�́AW�w�l�p�����P���敪�Ɂh0�F���s�h���́h1�F�w�l�h��ݒ肵�ĉ������B");

    /**
     * W�w�l�p���������P���̌����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01360 = errorMgr.defineErrorInfo(
            1360,
            "BUSINESS_ERROR_01360", 
            "W�w�l�p���������P�����P�ȏ�ɂ��ĉ������B");

    /**
     * W�w�l�p���������P���̌����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01361 = errorMgr.defineErrorInfo(
            1361,
            "BUSINESS_ERROR_01361", 
            "W�w�l�p���������P�����W���𒴂��Ă��܂��B");

    /**
     * W�w�l�p���������P���̐ݒ�l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01362 = errorMgr.defineErrorInfo(
            1362,
            "BUSINESS_ERROR_01362", 
            "W�w�l�p���������P���ɁA���l�ȊO�̕������܂܂�Ă��܂��B");

    /**
     * �U����敪�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01363 = errorMgr.defineErrorInfo(
            1363,
            "BUSINESS_ERROR_01363", 
            "�U����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �抷�\�����Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01364 = errorMgr.defineErrorInfo(
            1364,
            "BUSINESS_ERROR_01364", 
            "�抷�\�����Ȃ��G���[�B");

    /**
     * �戵�s��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01365 = errorMgr.defineErrorInfo(
            1365,
            "BUSINESS_ERROR_01365", 
            "�戵�s��Ȃ��B");

    /**
     * �D��s��ꗗ�Ɏ戵�s��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01366 = errorMgr.defineErrorInfo(
            1366,
            "BUSINESS_ERROR_01366", 
            "�D��s��ꗗ�Ɏ戵�s��Ȃ��B");

    /**
     * �w��s��͔���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01367 = errorMgr.defineErrorInfo(
            1367,
            "BUSINESS_ERROR_01367", 
            "�w��s��͔���B");

    /**
     * ����O�����\���R�[�h������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01368 = errorMgr.defineErrorInfo(
            1368,
            "BUSINESS_ERROR_01368", 
            "����O�����\���R�[�h������s�B");

    /**
     * �w�蕪���͎�t�\���ԊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01369 = errorMgr.defineErrorInfo(
            1369,
            "BUSINESS_ERROR_01369", 
            "�w�蕪���͎�t�\���ԊO�B");

    /**
     * �w��̗���O�����ɑ΂��钍�����o�^�ρB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01370 = errorMgr.defineErrorInfo(
            1370,
            "BUSINESS_ERROR_01370", 
            "�w��̗���O�����ɑ΂��钍�����o�^�ρB");

    /**
     * ����������O�����̈�l������\����������𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01371 = errorMgr.defineErrorInfo(
            1371,
            "BUSINESS_ERROR_01371", 
            "����������O�����̈�l������\����������𒴉߁B");

    /**
     * ����O�����������e�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01372 = errorMgr.defineErrorInfo(
            1372,
            "BUSINESS_ERROR_01372", 
            "����O�����͎w�l�̂ݎw��i���s�w��s�j�B");

    /**
     * ����O�����������e�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01373 = errorMgr.defineErrorInfo(
            1373,
            "BUSINESS_ERROR_01373", 
            "����O�����͏o����܂Œ����w��s�B");

    /**
     * ����O�����������e�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01374 = errorMgr.defineErrorInfo(
            1374,
            "BUSINESS_ERROR_01374", 
            "����O�����͎��s�����w��s�B");

    /**
     * ����O�����������e�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01375 = errorMgr.defineErrorInfo(
            1375,
            "BUSINESS_ERROR_01375", 
            "����O�����͔��������w��s�B");

    /**
     * ����O�����������e�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01376 = errorMgr.defineErrorInfo(
            1376,
            "BUSINESS_ERROR_01376", 
            "����O�����͒l�i�����w��s�B");

    /**
     * ����O�����E�����R�[�h�w��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01377 = errorMgr.defineErrorInfo(
            1377,
            "BUSINESS_ERROR_01377", 
            "����O�����Ŗ����R�[�h�w��Ȃ��B");

    /**
     * ����O�����E�s��R�[�h�w��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01378 = errorMgr.defineErrorInfo(
            1378,
            "BUSINESS_ERROR_01378", 
            "����O�����Ŏs��R�[�h�w��Ȃ��B");

    /**
     * �ē����[�������[���e�[�u���ɑ��݂��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01379 = errorMgr.defineErrorInfo(
            1379,
            "BUSINESS_ERROR_01379", 
            "�ē����[�������[���e�[�u���ɑ��݂��Ȃ��B");

    /**
     * �S���X���̊Ǘ��҂łȂ��ꍇ�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01380 = errorMgr.defineErrorInfo(
            1380,
            "BUSINESS_ERROR_01380", 
            "�S���X���̊Ǘ��҂łȂ��ꍇ�́A����s�B");

    /**
     * �ŐV�������z�M���ς̏ꍇ�́A�z�M�w���s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01381 = errorMgr.defineErrorInfo(
            1381,
            "BUSINESS_ERROR_01381", 
            "�ŐV�������z�M���ς̏ꍇ�́A�z�M�w���s�B");

    /**
     * �ŐV�������z�M�ς̏ꍇ�́A�z�M����s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01382 = errorMgr.defineErrorInfo(
            1382,
            "BUSINESS_ERROR_01382", 
            "�ŐV�������z�M�ς̏ꍇ�́A�z�M����s�B");

    /**
     * �ē����[���̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01383 = errorMgr.defineErrorInfo(
            1383,
            "BUSINESS_ERROR_01383", 
            "�ē����[���̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���ʂh�c���f�t�H���g�l�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01384 = errorMgr.defineErrorInfo(
            1384,
            "BUSINESS_ERROR_01384", 
            "���ʂh�c���f�t�H���g�l�ȊO�̒l�ł��B");

    /**
     * �ڋq�R�[�h�i���j�����w��̏ꍇ�A�ڋq�R�[�h�i���j���w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01385 = errorMgr.defineErrorInfo(
            1385,
            "BUSINESS_ERROR_01385", 
            "�ڋq�R�[�h�i���j�����w��̏ꍇ�A�ڋq�R�[�h�i���j���w��s�B");

    /**
     * �Y�����X�f�[�^�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01386 = errorMgr.defineErrorInfo(
            1386,
            "BUSINESS_ERROR_01386", 
            "�Y�����X�f�[�^�Ȃ�");

    /**
     * �Y���ڋq�f�[�^�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01387 = errorMgr.defineErrorInfo(
            1387,
            "BUSINESS_ERROR_01387", 
            "�Y���ڋq�f�[�^�Ȃ�");

    /**
     * is�X�V�폜�\����O��������( )==false�̏ꍇ�i����O�����������X�V�폜�s�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01388 = errorMgr.defineErrorInfo(
            1388,
            "BUSINESS_ERROR_01388", 
            "�w�肳�ꂽ����O���������f�[�^�͍X�V�^�폜���ł��܂���B");

    /**
     * ��t�J�n������null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01389 = errorMgr.defineErrorInfo(
            1389,
            "BUSINESS_ERROR_01389", 
            "��t�J�n���������w��ł��B");

    /**
     * ���P����null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01390 = errorMgr.defineErrorInfo(
            1390,
            "BUSINESS_ERROR_01390", 
            "�P�������w��ł��B");

    /**
     * ���P���������ȊO
���P���������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01391 = errorMgr.defineErrorInfo(
            1391,
            "BUSINESS_ERROR_01391", 
            "�P���������ȊO�ł��B");

    /**
     * ���P����0�ȉ�
���P����0�ȉ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01392 = errorMgr.defineErrorInfo(
            1392,
            "BUSINESS_ERROR_01392", 
            "�P����0�ȉ��ł��B");

    /**
     * ���P���̌�����8���𒴉�
���P���̌�����8���𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01393 = errorMgr.defineErrorInfo(
            1393,
            "BUSINESS_ERROR_01393", 
            "�P���̌�����8���𒴉߂ł��B");

    /**
     * �����`�Ԃ�null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01394 = errorMgr.defineErrorInfo(
            1394,
            "BUSINESS_ERROR_01394", 
            "�����`�Ԃ����w��ł��B");

    /**
     * �����`�Ԃ�����`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01395 = errorMgr.defineErrorInfo(
            1395,
            "BUSINESS_ERROR_01395", 
            "�����`�Ԃ̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����^�~�j���敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01396 = errorMgr.defineErrorInfo(
            1396,
            "BUSINESS_ERROR_01396", 
            "�����^�~�j���敪�����w��ł��B");

    /**
     * �����^�~�j���敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01397 = errorMgr.defineErrorInfo(
            1397,
            "BUSINESS_ERROR_01397", 
            "�����^�~�j���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����P�ʂ�null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01398 = errorMgr.defineErrorInfo(
            1398,
            "BUSINESS_ERROR_01398", 
            "�����P�ʂ����w��ł��B");

    /**
     * �����P�ʂ������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01399 = errorMgr.defineErrorInfo(
            1399,
            "BUSINESS_ERROR_01399", 
            "�����P�ʂ������ȊO�ł��B");

    /**
     * �����P�ʂ�0�ȉ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01400 = errorMgr.defineErrorInfo(
            1400,
            "BUSINESS_ERROR_01400", 
            "�����P�ʂ�0�ȉ��ł��B");

    /**
     * �����������P�ʂ�10����1�ɑ΂��Ĕ񐮐��{�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01401 = errorMgr.defineErrorInfo(
            1401,
            "BUSINESS_ERROR_01401", 
            "�����������P�ʂ�10����1�ɑ΂��Ĕ񐮐��{�ł��B");

    /**
     * �����敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01402 = errorMgr.defineErrorInfo(
            1402,
            "BUSINESS_ERROR_01402", 
            "�����敪�����w��ł��B");

    /**
     * �����敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01403 = errorMgr.defineErrorInfo(
            1403,
            "BUSINESS_ERROR_01403", 
            "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���P���������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01404 = errorMgr.defineErrorInfo(
            1404,
            "BUSINESS_ERROR_01404", 
            "���P���������ȊO�ł��B");

    /**
     * ���P����0�ȉ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01405 = errorMgr.defineErrorInfo(
            1405,
            "BUSINESS_ERROR_01405", 
            "���P����0�ȉ��ł��B");

    /**
     * ���P���̌�����8���𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01406 = errorMgr.defineErrorInfo(
            1406,
            "BUSINESS_ERROR_01406", 
            "���P���̌�����8���𒴉߂ł��B");

    /**
     * ���i�ʎ戵�󋵈ꗗ��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01407 = errorMgr.defineErrorInfo(
            1407,
            "BUSINESS_ERROR_01407", 
            "���i�ʎ戵�󋵈ꗗ�����w��ł��B");

    /**
     * ���i�ʎ戵�󋵈ꗗ�̗v�f����0�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01408 = errorMgr.defineErrorInfo(
            1408,
            "BUSINESS_ERROR_01408", 
            "���i�ʎ戵�󋵈ꗗ�̗v�f����0�ł��B");

    /**
     * ���O�C�����敪�G���[(����`�̒l)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01409 = errorMgr.defineErrorInfo(
            1409,
            "BUSINESS_ERROR_01409", 
            "���O�C�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���́^����敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01410 = errorMgr.defineErrorInfo(
            1410,
            "BUSINESS_ERROR_01410", 
            "���́^����敪�����w��ł��B");

    /**
     * ���́^����敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01411 = errorMgr.defineErrorInfo(
            1411,
            "BUSINESS_ERROR_01411", 
            "���́^����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �]���P���������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01412 = errorMgr.defineErrorInfo(
            1412,
            "BUSINESS_ERROR_01412", 
            "�]���P���������ȊO�ł��B");

    /**
     * �]���P����0�ȉ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01413 = errorMgr.defineErrorInfo(
            1413,
            "BUSINESS_ERROR_01413", 
            "�]���P����0�ȉ��ł��B");

    /**
     * �]���P���̌�����8���𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01414 = errorMgr.defineErrorInfo(
            1414,
            "BUSINESS_ERROR_01414", 
            "�]���P���̌�����8���𒴉߂ł��B");

    /**
     * �\���Ώۂ�����`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01415 = errorMgr.defineErrorInfo(
            1415,
            "BUSINESS_ERROR_01415", 
            "�\���Ώۂ̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���O�C�����󋵈ꗗ��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01416 = errorMgr.defineErrorInfo(
            1416,
            "BUSINESS_ERROR_01416", 
            "���O�C�����󋵈ꗗ�����w��ł��B");

    /**
     * ���O�C�����󋵈ꗗ�̗v�f����0�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01417 = errorMgr.defineErrorInfo(
            1417,
            "BUSINESS_ERROR_01417", 
            "���O�C�����󋵈ꗗ�̗v�f����0�ł��B");

    /**
     * �|�[�g�t�H���I�R�[�h��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01418 = errorMgr.defineErrorInfo(
            1418,
            "BUSINESS_ERROR_01418", 
            "�|�[�g�t�H���I�R�[�h�����w��ł��B");

    /**
     * �Ώۖ��ׂ̎w��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01419 = errorMgr.defineErrorInfo(
            1419,
            "BUSINESS_ERROR_01419", 
            "�Ώۖ��ׂ̎w��Ȃ��ł��B");

    /**
     * ���P����null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01420 = errorMgr.defineErrorInfo(
            1420,
            "BUSINESS_ERROR_01420", 
            "���P�������w��ł��B");

    /**
     * �����~�敪�G���[(����`�̒l)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01421 = errorMgr.defineErrorInfo(
            1421,
            "BUSINESS_ERROR_01421", 
            "�����~�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �s��ʎ���󋵈ꗗ��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01422 = errorMgr.defineErrorInfo(
            1422,
            "BUSINESS_ERROR_01422", 
            "�s��ʎ���󋵈ꗗ�����w��ł��B");

    /**
     * �s��ʎ���󋵈ꗗ�̗v�f����0�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01423 = errorMgr.defineErrorInfo(
            1423,
            "BUSINESS_ERROR_01423", 
            "�s��ʎ���󋵈ꗗ�̗v�f����0�ł��B");

    /**
     * ������t���i��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01424 = errorMgr.defineErrorInfo(
            1424,
            "BUSINESS_ERROR_01424", 
            "������t���i�����w��ł��B");

    /**
     * ������t�g�����U�N�V������null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01425 = errorMgr.defineErrorInfo(
            1425,
            "BUSINESS_ERROR_01425", 
            "������t�g�����U�N�V���������w��ł��B");

    /**
     * ���X�ʎ戵�󋵈ꗗ�̗v�f����0�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01426 = errorMgr.defineErrorInfo(
            1426,
            "BUSINESS_ERROR_01426", 
            "���X�ʎ戵�󋵈ꗗ�̗v�f����0�ł��B");

    /**
     * ���X�ʎ戵�󋵈ꗗ��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01427 = errorMgr.defineErrorInfo(
            1427,
            "BUSINESS_ERROR_01427", 
            "���X�ʎ戵�󋵈ꗗ�����w��ł��B");

    /**
     * �ڋq�����ʎ����~���null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01428 = errorMgr.defineErrorInfo(
            1428,
            "BUSINESS_ERROR_01428", 
            "�ڋq�����ʎ����~��񂪖��w��ł��B");

    /**
     * ���X�R�[�h�ꗗ��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01429 = errorMgr.defineErrorInfo(
            1429,
            "BUSINESS_ERROR_01429", 
            "���X�R�[�h�ꗗ�����w��ł��B");

    /**
     * �L������From��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01430 = errorMgr.defineErrorInfo(
            1430,
            "BUSINESS_ERROR_01430", 
            "�L������From�����w��ł��B");

    /**
     * �L������From�G���[(���݂��Ȃ����t)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01431 = errorMgr.defineErrorInfo(
            1431,
            "BUSINESS_ERROR_01431", 
            "�L������From�G���[(���݂��Ȃ����t)");

    /**
     * �L������To��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01432 = errorMgr.defineErrorInfo(
            1432,
            "BUSINESS_ERROR_01432", 
            "�L������To�����w��ł��B");

    /**
     * �L������To�G���[(���݂��Ȃ����t)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01433 = errorMgr.defineErrorInfo(
            1433,
            "BUSINESS_ERROR_01433", 
            "�L������To�G���[(���݂��Ȃ����t)");

    /**
     * �L�������������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01434 = errorMgr.defineErrorInfo(
            1434,
            "BUSINESS_ERROR_01434", 
            "�L�������������G���[");

    /**
     * ���͗��R�G���[(��������)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01435 = errorMgr.defineErrorInfo(
            1435,
            "BUSINESS_ERROR_01435", 
            "���͗��R�G���[(��������)");

    /**
     * �ڋq�����~���ꗗ��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01436 = errorMgr.defineErrorInfo(
            1436,
            "BUSINESS_ERROR_01436", 
            "�ڋq�����~���ꗗ�����w��ł��B");

    /**
     * �ڋq�����~���ꗗ�̗v�f����0�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01437 = errorMgr.defineErrorInfo(
            1437,
            "BUSINESS_ERROR_01437", 
            "�ڋq�����~���ꗗ�̗v�f����0�ł��B");

    /**
     * ������ʂ�null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01438 = errorMgr.defineErrorInfo(
            1438,
            "BUSINESS_ERROR_01438", 
            "������ʂ����w��ł��B");

    /**
     * ������ʂ�����`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01439 = errorMgr.defineErrorInfo(
            1439,
            "BUSINESS_ERROR_01439", 
            "������ʂ̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �m�F���e��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01440 = errorMgr.defineErrorInfo(
            1440,
            "BUSINESS_ERROR_01440", 
            "�m�F���e�����w��ł��B");

    /**
     * ���͐��l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01441 = errorMgr.defineErrorInfo(
            1441,
            "BUSINESS_ERROR_01441", 
            "���͐��l�G���[");

    /**
     * ���͓��t�G���[(�K�p����From)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01442 = errorMgr.defineErrorInfo(
            1442,
            "BUSINESS_ERROR_01442", 
            "���͓��t�G���[(�K�p����From)");

    /**
     * ���͓��t�G���[(�K�p����To)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01443 = errorMgr.defineErrorInfo(
            1443,
            "BUSINESS_ERROR_01443", 
            "���͓��t�G���[(�K�p����To)");

    /**
     * ���t�����̓G���[(�K�p����From)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01444 = errorMgr.defineErrorInfo(
            1444,
            "BUSINESS_ERROR_01444", 
            "���t�����̓G���[(�K�p����From)");

    /**
     * ���͓��t�G���[(�K�p����To���������t����)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01445 = errorMgr.defineErrorInfo(
            1445,
            "BUSINESS_ERROR_01445", 
            "���͓��t�G���[(�K�p����To���������t����)");

    /**
     * �K�p����From/To�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01446 = errorMgr.defineErrorInfo(
            1446,
            "BUSINESS_ERROR_01446", 
            "�K�p����From/To�������G���[");

    /**
     * �區�ڋ敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01447 = errorMgr.defineErrorInfo(
            1447,
            "BUSINESS_ERROR_01447", 
            "�區�ڋ敪�����w��ł��B");

    /**
     * �區�ڋ敪�̗v�f����0�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01448 = errorMgr.defineErrorInfo(
            1448,
            "BUSINESS_ERROR_01448", 
            "�區�ڋ敪�̗v�f����0�ł��B");

    /**
     * �區�ڋ敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01449 = errorMgr.defineErrorInfo(
            1449,
            "BUSINESS_ERROR_01449", 
            "�區�ڋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����ڋ敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01450 = errorMgr.defineErrorInfo(
            1450,
            "BUSINESS_ERROR_01450", 
            "�����ڋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �c�Ɠ���null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01451 = errorMgr.defineErrorInfo(
            1451,
            "BUSINESS_ERROR_01451", 
            "�c�Ɠ������w��ł��B");

    /**
     * ��t�I��������null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01452 = errorMgr.defineErrorInfo(
            1452,
            "BUSINESS_ERROR_01452", 
            "��t�I�����������w��ł��B");

    /**
     * �������i�������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01453 = errorMgr.defineErrorInfo(
            1453,
            "BUSINESS_ERROR_01453", 
            "�������i�������ȊO�̒l�ł��B");

    /**
     * �������i��0�ȉ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01454 = errorMgr.defineErrorInfo(
            1454,
            "BUSINESS_ERROR_01454", 
            "�������i��0�ȉ��̒l�ł��B");

    /**
     * �������i�̌�����9���𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01455 = errorMgr.defineErrorInfo(
            1455,
            "BUSINESS_ERROR_01455", 
            "�������i�̃T�C�Y���s���ł��B");

    /**
     * �\����������������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01456 = errorMgr.defineErrorInfo(
            1456,
            "BUSINESS_ERROR_01456", 
            "�\����������������ȊO�̒l�ł��B");

    /**
     * �\�����������0�ȉ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01457 = errorMgr.defineErrorInfo(
            1457,
            "BUSINESS_ERROR_01457", 
            "�\�����������0�ȉ��̒l�ł��B");

    /**
     * �\����������̌�����8���𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01458 = errorMgr.defineErrorInfo(
            1458,
            "BUSINESS_ERROR_01458", 
            "�\����������̃T�C�Y���s���ł��B");

    /**
     * ����O���������E��t�J�n�����ɂ́A�ߋ������w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01459 = errorMgr.defineErrorInfo(
            1459,
            "BUSINESS_ERROR_01459", 
            "����O���������E��t�J�n�����ɂ́A�ߋ��������w��ł��܂���B");

    /**
     * ����O���������E��t�J�n�����ɂ́A�،����.����O������t�J�n�����O�͎w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01460 = errorMgr.defineErrorInfo(
            1460,
            "BUSINESS_ERROR_01460", 
            "����O���������E��t�J�n�����ɂ́A�،����.����O������t�J�n�����O���w��ł��܂���B");

    /**
     * �d�����镪�������f�[�^�����ɓo�^�ς̏ꍇ�i�d�����镪�������f�[�^�����ɓo�^�ρj�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01461 = errorMgr.defineErrorInfo(
            1461,
            "BUSINESS_ERROR_01461", 
            "�e�[�u���ɏd������Y���f�[�^�����݂��܂��B");

    /**
     * ���i�敪�ꗗ��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01462 = errorMgr.defineErrorInfo(
            1462,
            "BUSINESS_ERROR_01462", 
            "���i�敪�ꗗ�����w��ł��B");

    /**
     * ���i�敪�ꗗ�̗v�f����0�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01463 = errorMgr.defineErrorInfo(
            1463,
            "BUSINESS_ERROR_01463", 
            "���i�敪�ꗗ�̗v�f����0");

    /**
     * �w����ʂ�����`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01464 = errorMgr.defineErrorInfo(
            1464,
            "BUSINESS_ERROR_01464", 
            "�w����ʂ̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �W�v�敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01465 = errorMgr.defineErrorInfo(
            1465,
            "BUSINESS_ERROR_01465", 
            "�W�v�敪�����w��ł��B");

    /**
     * �W�v�敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01466 = errorMgr.defineErrorInfo(
            1466,
            "BUSINESS_ERROR_01466", 
            "�W�v�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���ʏW�v�Ώ۔N����null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01467 = errorMgr.defineErrorInfo(
            1467,
            "BUSINESS_ERROR_01467", 
            "���ʏW�v�Ώ۔N�������w��ł��B");

    /**
     * ���ʏW�v�Ώ۔N���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01468 = errorMgr.defineErrorInfo(
            1468,
            "BUSINESS_ERROR_01468", 
            "���ʏW�v�Ώ۔N���G���[�B");

    /**
     * ���ʏW�v�Ώۋ敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01469 = errorMgr.defineErrorInfo(
            1469,
            "BUSINESS_ERROR_01469", 
            "���ʏW�v�Ώۋ敪�����w��ł��B");

    /**
     * ���ʏW�v�Ώۋ敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01470 = errorMgr.defineErrorInfo(
            1470,
            "BUSINESS_ERROR_01470", 
            "���ʏW�v�Ώۋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����o�H�\���敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01471 = errorMgr.defineErrorInfo(
            1471,
            "BUSINESS_ERROR_01471", 
            "�����o�H�\���敪�����w��ł��B");

    /**
     * �����o�H�\���敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01472 = errorMgr.defineErrorInfo(
            1472,
            "BUSINESS_ERROR_01472", 
            "�����o�H�\���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �s��\���敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01473 = errorMgr.defineErrorInfo(
            1473,
            "BUSINESS_ERROR_01473", 
            "�s��\���敪�����w��ł��B");

    /**
     * �s��\���敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01474 = errorMgr.defineErrorInfo(
            1474,
            "BUSINESS_ERROR_01474", 
            "�s��\���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �s�g���i�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01475 = errorMgr.defineErrorInfo(
            1475,
            "BUSINESS_ERROR_01475", 
            "�s�g���i�G���[�B");

    /**
     * ����ID�������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01476 = errorMgr.defineErrorInfo(
            1476,
            "BUSINESS_ERROR_01476", 
            "����ID�������ȊO�ł��B");

    /**
     * ������ԋ敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01477 = errorMgr.defineErrorInfo(
            1477,
            "BUSINESS_ERROR_01477", 
            "������ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ��������敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01478 = errorMgr.defineErrorInfo(
            1478,
            "BUSINESS_ERROR_01478", 
            "��������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���͎��ԃG���[(��������From)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01479 = errorMgr.defineErrorInfo(
            1479,
            "BUSINESS_ERROR_01479", 
            "���͎��ԃG���[(��������From)�B");

    /**
     * ���͎��ԃG���[(��������To)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01480 = errorMgr.defineErrorInfo(
            1480,
            "BUSINESS_ERROR_01480", 
            "���͎��ԃG���[(��������To�j�B");

    /**
     * ���͎��Ԑ������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01481 = errorMgr.defineErrorInfo(
            1481,
            "BUSINESS_ERROR_01481", 
            "���͎��Ԑ������G���[�B");

    /**
     * ���͎��ԃG���[(��n��)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01482 = errorMgr.defineErrorInfo(
            1482,
            "BUSINESS_ERROR_01482", 
            "���͎��ԃG���[(��n��)�B");

    /**
     * ���{���\�߃G���[���b�Z�[�W�̔ԃi���o�[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01685 = errorMgr.defineErrorInfo(
            1685,
            "BUSINESS_ERROR_01685", 
            "���{���\�߃G���[���b�Z�[�W�̔ԃi���o�[�B");

    /**
     * �⍇�����i���j�͖⍇�����i���j���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01686 = errorMgr.defineErrorInfo(
            1686,
            "BUSINESS_ERROR_01686", 
            "�⍇�����i���j�͖⍇�����i���j���傫���ł��B");

    /**
     * �@@�\�h�c�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01687 = errorMgr.defineErrorInfo(
            1687,
            "BUSINESS_ERROR_01687", 
            "�@@�\�h�c�����w��ł��B");

    /**
     * �⍇���R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01688 = errorMgr.defineErrorInfo(
            1688,
            "BUSINESS_ERROR_01688", 
            "�⍇���R�[�h�����w��ł��B");

    /**
     * �⍇����񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01689 = errorMgr.defineErrorInfo(
            1689,
            "BUSINESS_ERROR_01689", 
            "�⍇����񂪖��w��ł��B");

    /**
     * �ڋq�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01690 = errorMgr.defineErrorInfo(
            1690,
            "BUSINESS_ERROR_01690", 
            "�ڋq�����w��ł��B");

    /**
     * �ڋq�����S�p�����ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01691 = errorMgr.defineErrorInfo(
            1691,
            "BUSINESS_ERROR_01691", 
            "�ڋq�����S�p�����ł͂���܂���B");

    /**
     * �⍇�������̌������������z���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01692 = errorMgr.defineErrorInfo(
            1692,
            "BUSINESS_ERROR_01692", 
            "�⍇�������̌������������z���Ă��܂��B");

    /**
     * ���[���A�h���X�̌������������z���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01693 = errorMgr.defineErrorInfo(
            1693,
            "BUSINESS_ERROR_01693", 
            "���[���A�h���X�̌������������z���Ă��܂��B");

    /**
     * �⍇�����e�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01694 = errorMgr.defineErrorInfo(
            1694,
            "BUSINESS_ERROR_01694", 
            "�⍇�����e�����w��ł��B");

    /**
     * �⍇�����e�̌������������z���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01695 = errorMgr.defineErrorInfo(
            1695,
            "BUSINESS_ERROR_01695", 
            "�⍇�����e�̌������������z���Ă��܂��B");

    /**
     * �⍇�����e���S�p�����ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01696 = errorMgr.defineErrorInfo(
            1696,
            "BUSINESS_ERROR_01696", 
            "�⍇�����e���S�p�����ł͂���܂���B");

    /**
     * ����ԋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01697 = errorMgr.defineErrorInfo(
            1697,
            "BUSINESS_ERROR_01697", 
            "����ԋ敪�́A�ݒ肵�Ȃ����w0:�����x�C�w1:�ꕔ���x�C�w2:�S�����x��ݒ肵�ĉ������B");

    /**
     * �\�[�g�E�L�[�`�F�b�N�i����p�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01698 = errorMgr.defineErrorInfo(
            1698,
            "BUSINESS_ERROR_01698", 
            "�\�[�g�L�[.�L�[���ڂ̒l�g�����R�[�h�h�A�g�s��R�[�h �h�A�g��t�J�n���� �h�A�g��t�I������ �h�ȊO���A�ݒ肵�Ȃ��ŉ������B");

    /**
     * ������t���i�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01699 = errorMgr.defineErrorInfo(
            1699,
            "BUSINESS_ERROR_01699", 
            "������t���i�́A�ݒ肵�Ȃ����w01:�����x�C�w02:�����~�j�����x�C�w03:�M�p����x��ݒ肵�ĉ������B");

    /**
     * ���[���A�h���X�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01700 = errorMgr.defineErrorInfo(
            1700,
            "BUSINESS_ERROR_01700", 
            "���[���A�h���X�����w��ł��B");

    /**
     * ������t��~�J�n���Ԃ̎w��\���Ԃ͈͓̔��ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01701 = errorMgr.defineErrorInfo(
            1701,
            "BUSINESS_ERROR_01701", 
            "������t��~�J�n���Ԃ̎w��\���Ԃ͈͓̔��ł͂���܂���B");

    /**
     * ������t��~�J�n���Ԃ̎w�肪HOST�o�^���Ԃ���̎��Ԃ��w�肳��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01702 = errorMgr.defineErrorInfo(
            1702,
            "BUSINESS_ERROR_01702", 
            "������t��~�J�n���Ԃ̎w�肪HOST�o�^���Ԃ���̎��Ԃ��w�肳��Ă��܂��B");

    /**
     * �M�p����������J�݁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01703 = errorMgr.defineErrorInfo(
            1703,
            "BUSINESS_ERROR_01703", 
            "�M�p�̓�������J�݂Ȃ��B");

    /**
     * �J�e�S���[���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01705 = errorMgr.defineErrorInfo(
            1705,
            "BUSINESS_ERROR_01705", 
            "�J�e�S���[���̕��������������z���Ă��܂��B");

    /**
     * �T�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01706 = errorMgr.defineErrorInfo(
            1706,
            "BUSINESS_ERROR_01706", 
            "�T�v�̕��������������z���Ă��܂��B");

    /**
     * �J�e�S���[���e�����ӏ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01707 = errorMgr.defineErrorInfo(
            1707,
            "BUSINESS_ERROR_01707", 
            "�J�e�S���[���e�̒���������Ă��܂���B");

    /**
     * �i�i���戵���Ă��邩�ǂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01708 = errorMgr.defineErrorInfo(
            1708,
            "BUSINESS_ERROR_01708", 
            "�Y������J�e�S���[�ň����Ă���i�i������܂��B");

    /**
     * �i�i�ԍ����d�����ĂȂ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01709 = errorMgr.defineErrorInfo(
            1709,
            "BUSINESS_ERROR_01709", 
            "�i�i�ԍ����d���ƂȂ��Ă���܂��B");

    /**
     * �i�i�ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01710 = errorMgr.defineErrorInfo(
            1710,
            "BUSINESS_ERROR_01710", 
            "�i�i�ԍ��̌������������z���Ă��܂��B");

    /**
     * �i�i�ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01711 = errorMgr.defineErrorInfo(
            1711,
            "BUSINESS_ERROR_01711", 
            "�i�i�ԍ��Ɂu���p�p�����v�A�u-�i�n�C�t���j�v�ȊO�̕������܂܂�Ă��܂��B");

    /**
     * �i�i���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01712 = errorMgr.defineErrorInfo(
            1712,
            "BUSINESS_ERROR_01712", 
            "�i�i���̕��������������z���Ă��܂��B");

    /**
     * �K�v�|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01713 = errorMgr.defineErrorInfo(
            1713,
            "BUSINESS_ERROR_01713", 
            "�K�v�|�C���g�̌������������z���Ă��܂��B");

    /**
     * �K�v�|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01714 = errorMgr.defineErrorInfo(
            1714,
            "BUSINESS_ERROR_01714", 
            "�K�v�|�C���g��0�ȉ��̒l�ł��B");

    /**
     * �K�v�|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01715 = errorMgr.defineErrorInfo(
            1715,
            "BUSINESS_ERROR_01715", 
            "�K�v�|�C���g�̒l�����l�ȊO�̒l�ł��B");

    /**
     * �񋟊��ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01716 = errorMgr.defineErrorInfo(
            1716,
            "BUSINESS_ERROR_01716", 
            "�񋟊J�n�����A�܂��͒񋟏I�����������w��ł��B");

    /**
     * �񋟊��ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01717 = errorMgr.defineErrorInfo(
            1717,
            "BUSINESS_ERROR_01717", 
            "�񋟊J�n�����͒񋟏I���������傫���ł��B");

    /**
     * �i�i���e�����ӏ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01718 = errorMgr.defineErrorInfo(
            1718,
            "BUSINESS_ERROR_01718", 
            "�i�i���e�̒���������Ă��܂���B");

    /**
     * �����|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01719 = errorMgr.defineErrorInfo(
            1719,
            "BUSINESS_ERROR_01719", 
            "�����|�C���g�����w��ł��B");

    /**
     * �����|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01720 = errorMgr.defineErrorInfo(
            1720,
            "BUSINESS_ERROR_01720", 
            "�����|�C���g��0�ȉ��̒l�ł��B");

    /**
     * �����|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01721 = errorMgr.defineErrorInfo(
            1721,
            "BUSINESS_ERROR_01721", 
            "�����|�C���g�̒l�����l�ȊO�̒l�ł��B");

    /**
     * �����|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01722 = errorMgr.defineErrorInfo(
            1722,
            "BUSINESS_ERROR_01722", 
            "�����|�C���g�̌������������z���Ă��܂��B");

    /**
     * ������|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01724 = errorMgr.defineErrorInfo(
            1724,
            "BUSINESS_ERROR_01724", 
            "������̗��p�\�|�C���g���}�C�i�X�ɂȂ�܂��B");

    /**
     * �|�C���g�]�͂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01725 = errorMgr.defineErrorInfo(
            1725,
            "BUSINESS_ERROR_01725", 
            "�����������ƁA���q�l�̂����p�\�|�C���g���}�C�i�X�ƂȂ�܂��B");

    /**
     * �i�i�̒񋟊��ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01726 = errorMgr.defineErrorInfo(
            1726,
            "BUSINESS_ERROR_01726", 
            "���̌i�i�́A���ݎ戵���Ă���܂���B");

    /**
     * �i�i�ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01727 = errorMgr.defineErrorInfo(
            1727,
            "BUSINESS_ERROR_01727", 
            "�i�i�ԍ������w��ł��B");

    /**
     * �J�e�S���[�ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01728 = errorMgr.defineErrorInfo(
            1728,
            "BUSINESS_ERROR_01728", 
            "�J�e�S���[�ԍ������w��ł��B");

    /**
     * ���X�R�[�h�̒l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01729 = errorMgr.defineErrorInfo(
            1729,
            "BUSINESS_ERROR_01729", 
            "���X�R�[�h�����l�ȊO�̒l�ł��B");

    /**
     * �m�F�������O���p�\�|�C���g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01731 = errorMgr.defineErrorInfo(
            1731,
            "BUSINESS_ERROR_01731", 
            "�m�F���̗��p�\�|�C���g�ƌ��݂̗��p�\�|�C���g����v���܂���B");

    /**
     * �\��ID�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01732 = errorMgr.defineErrorInfo(
            1732,
            "BUSINESS_ERROR_01732", 
            "�\��ID�����w��ł��B");

    /**
     * �\��ID�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01733 = errorMgr.defineErrorInfo(
            1733,
            "BUSINESS_ERROR_01733", 
            "�\��ID�̃T�C�Y���s���ł��B");

    /**
     * �Y�������͕ύX�̎�t�ς܂��͔������̏�ԁB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01734 = errorMgr.defineErrorInfo(
            1734,
            "BUSINESS_ERROR_01734", 
            "�Y�������͕ύX�̎�t�ς܂��͔������̏�ԁB");

    /**
     * IPO�F�Y���ڋq���⌇�҂Ŋ��Ɏ��ނ��Ă���ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01735 = errorMgr.defineErrorInfo(
            1735,
            "BUSINESS_ERROR_01735", 
            "�Y���ڋq�͕⌇�҂ŁA���Ɏ��ނ��Ă��܂��B");

    /**
     * �敨�^�I�v�V�����敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01736 = errorMgr.defineErrorInfo(
            1736,
            "BUSINESS_ERROR_01736", 
            "�敨�^�I�v�V�����敪�����w��ł��B");

    /**
     * �敨�^�I�v�V�����敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01737 = errorMgr.defineErrorInfo(
            1737,
            "BUSINESS_ERROR_01737", 
            "�敨�^�I�v�V�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �L���R�[�h�l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01738 = errorMgr.defineErrorInfo(
            1738,
            "BUSINESS_ERROR_01738", 
            "���ڒl���L���ȃR�[�h�l�ł͂���܂���B");

    /**
     * PR�w�ێ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01739 = errorMgr.defineErrorInfo(
            1739,
            "BUSINESS_ERROR_01739", 
            "PR�w�ێ���񂪖��w��ł��B");

    /**
     * PR�w�ێ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01740 = errorMgr.defineErrorInfo(
            1740,
            "BUSINESS_ERROR_01740", 
            "PR�w�ێ����̊e���������w��ł��B");

    /**
     * IPO:�i�⌇���I�̏ꍇ�j���ނ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01741 = errorMgr.defineErrorInfo(
            1741,
            "BUSINESS_ERROR_01741", 
            "�Y���ڋq�͕⌇���I�҂ł���AIPO�\���͎��ލςɂȂ��Ă��܂��B");

    /**
     * IPO:validate���ڃ����O�X�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01742 = errorMgr.defineErrorInfo(
            1742,
            "BUSINESS_ERROR_01742", 
            "�A�b�v���[�h�t�@@�C�����̍��ڒ��ōő�l�𒴂��Ă�����̂�����܂��B");

    /**
     * IPO:�J�㒊�I�ŗ��I���Ă���ꍇ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01743 = errorMgr.defineErrorInfo(
            1743,
            "BUSINESS_ERROR_01743", 
            "�Y���ڋq�͌J�㒊�I�ŗ��I���Ă��܂��B");

    /**
     * IPO:�w���\���I��(�ژ_�����L��)�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01744 = errorMgr.defineErrorInfo(
            1744,
            "BUSINESS_ERROR_01744", 
            "�w���\���i�ژ_�����L�ځj�͏I�����Ă��܂��B");

    /**
     * IPO:�V�K�\���������ޯ�����ިݸޏI���O�łȂ��ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01745 = errorMgr.defineErrorInfo(
            1745,
            "BUSINESS_ERROR_01745", 
            "�Y���u�b�N�r���f�B���O�\���̐V�K�\�������̓u�b�N�r���f�B���O�̏I���������߂��Ă��܂��B");

    /**
     * IPO:IPO�\��������ς݂̏ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01746 = errorMgr.defineErrorInfo(
            1746,
            "BUSINESS_ERROR_01746", 
            "�Y���u�b�N�r���f�B���O�\���͊��ɐ\���������Ă��܂��B");

    /**
     * �s��R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01747 = errorMgr.defineErrorInfo(
            1747,
            "BUSINESS_ERROR_01747", 
            "�s��R�[�h�`�F�b�N�G���[�i�s������~���j�B");

    /**
     * FX���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01748 = errorMgr.defineErrorInfo(
            1748,
            "BUSINESS_ERROR_01748", 
            "FX�������������w��ł��B");

    /**
     * �X�V��X�e�[�^�X�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01749 = errorMgr.defineErrorInfo(
            1749,
            "BUSINESS_ERROR_01749", 
            "�X�V��X�e�[�^�X�敪�����w��ł��B");

    /**
     * �X�V��X�e�[�^�X�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01750 = errorMgr.defineErrorInfo(
            1750,
            "BUSINESS_ERROR_01750", 
            "�X�V��X�e�[�^�X�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01751 = errorMgr.defineErrorInfo(
            1751,
            "BUSINESS_ERROR_01751", 
            "FX�������ꗗ�����w��ł��B");

    /**
     * FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01752 = errorMgr.defineErrorInfo(
            1752,
            "BUSINESS_ERROR_01752", 
            "FX�������ꗗ�̗v�f�����O�ł��B");

    /**
     * FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01753 = errorMgr.defineErrorInfo(
            1753,
            "BUSINESS_ERROR_01753", 
            "FX�������̃R�[�X�敪�����w��ł��B");

    /**
     * FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01754 = errorMgr.defineErrorInfo(
            1754,
            "BUSINESS_ERROR_01754", 
            "FX�������̃R�[�X�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01755 = errorMgr.defineErrorInfo(
            1755,
            "BUSINESS_ERROR_01755", 
            "�X�V��X�e�[�^�X���h�����J�݊����h�̏ꍇ�́AFX�������̌����ԍ����s���ł��B");

    /**
     * FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01756 = errorMgr.defineErrorInfo(
            1756,
            "BUSINESS_ERROR_01756", 
            "�X�V��X�e�[�^�X���h�폜�h�̏ꍇ�́AFX�������̌����ԍ����w��s�ł��B");

    /**
     * ���X�R�[�h�̗v�f�����O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01757 = errorMgr.defineErrorInfo(
            1757,
            "BUSINESS_ERROR_01757", 
            "���X�R�[�h�̗v�f�����O�ł��B");

    /**
     * �X�e�[�^�X�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01758 = errorMgr.defineErrorInfo(
            1758,
            "BUSINESS_ERROR_01758", 
            "�X�e�[�^�X�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * MRF�����󋵋敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01759 = errorMgr.defineErrorInfo(
            1759,
            "BUSINESS_ERROR_01759", 
            "MRF�����󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �\�����i���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01760 = errorMgr.defineErrorInfo(
            1760,
            "BUSINESS_ERROR_01760", 
            "�\�����i���j�̓��͒l���s���ł��B");

    /**
     * �\�����i���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01761 = errorMgr.defineErrorInfo(
            1761,
            "BUSINESS_ERROR_01761", 
            "�\�����i���j�̓��͒l���s���ł��B");

    /**
     * �\�����i���`���j�������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01762 = errorMgr.defineErrorInfo(
            1762,
            "BUSINESS_ERROR_01762", 
            "�\�����i���j�͐\�����i���j���傫���ł��B");

    /**
     * ���͔ԍ��敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01763 = errorMgr.defineErrorInfo(
            1763,
            "BUSINESS_ERROR_01763", 
            "���͔ԍ��敪�����w��ł��B");

    /**
     * ���͔ԍ��敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01764 = errorMgr.defineErrorInfo(
            1764,
            "BUSINESS_ERROR_01764", 
            "���͔ԍ��敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���͔ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01765 = errorMgr.defineErrorInfo(
            1765,
            "BUSINESS_ERROR_01765", 
            "���͔ԍ������w��ł��B");

    /**
     * ���͔ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01766 = errorMgr.defineErrorInfo(
            1766,
            "BUSINESS_ERROR_01766", 
            "���͔ԍ������l�ȊO�̒l�ł��B");

    /**
     * �X�V�チ�[���A�h���X�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01767 = errorMgr.defineErrorInfo(
            1767,
            "BUSINESS_ERROR_01767", 
            "�����敪���h�������ύX�h�̏ꍇ�́A�X�V�チ�[���A�h���X�����w��ł��B");

    /**
     * �X�V��FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01768 = errorMgr.defineErrorInfo(
            1768,
            "BUSINESS_ERROR_01768", 
            "�����敪���h�������ύX�h�̏ꍇ�́A�X�V��FX�������ꗗ�����w��ł��B");

    /**
     * �X�V��FX�������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01769 = errorMgr.defineErrorInfo(
            1769,
            "BUSINESS_ERROR_01769", 
            "�����敪���h�������ύX�h�̏ꍇ�́A�X�V��FX�������ꗗ�̗v�f�����O�ł��B");

    /**
     * �X�V������J�ݏ󋵋敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01770 = errorMgr.defineErrorInfo(
            1770,
            "BUSINESS_ERROR_01770", 
            "�����敪���h�����J�ݏ󋵕ύX�h�̏ꍇ�́A�X�V������J�ݏ󋵋敪�����w��ł��B");

    /**
     * �X�V������J�ݏ󋵋敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01771 = errorMgr.defineErrorInfo(
            1771,
            "BUSINESS_ERROR_01771", 
            "�����敪���h�����J�ݏ󋵕ύX�h�̏ꍇ�́A�X�V������J�ݏ󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �U�֋敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01772 = errorMgr.defineErrorInfo(
            1772,
            "BUSINESS_ERROR_01772", 
            "�U�֋敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ��t���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01773 = errorMgr.defineErrorInfo(
            1773,
            "BUSINESS_ERROR_01773", 
            "��t���i���j�͎�t���i���j�ȏ�ł��B");

    /**
     * FX���������ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01774 = errorMgr.defineErrorInfo(
            1774,
            "BUSINESS_ERROR_01774", 
            "FX���������ꗗ�����w��ł��B");

    /**
     * �Ïؔԍ��`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01784 = errorMgr.defineErrorInfo(
            1784,
            "BUSINESS_ERROR_01784", 
            "�Ïؔԍ��̌���������������܂���B");

    /**
     * ���[���A�h���X�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01788 = errorMgr.defineErrorInfo(
            1788,
            "BUSINESS_ERROR_01788", 
            "�m�F�p�̃��[���A�h���X�������͂ł��B");

    /**
     * ���[���A�h���X�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01789 = errorMgr.defineErrorInfo(
            1789,
            "BUSINESS_ERROR_01789", 
            "���[���A�h���X���m�F�p�̂��̂ƈ�v���Ă���܂���B");

    /**
     * �Ïؔԍ��`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01790 = errorMgr.defineErrorInfo(
            1790,
            "BUSINESS_ERROR_01790", 
            "�m�F�p�̈Ïؔԍ��������͂ł��B");

    /**
     * �Ïؔԍ��`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01791 = errorMgr.defineErrorInfo(
            1791,
            "BUSINESS_ERROR_01791", 
            "�Ïؔԍ����m�F�p�̂��̂ƈ�v���Ă���܂���B");

    /**
     * �R�[�X�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01792 = errorMgr.defineErrorInfo(
            1792,
            "BUSINESS_ERROR_01792", 
            "�R�[�X�敪�����w��ł��B");

    /**
     * �R�[�X�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01793 = errorMgr.defineErrorInfo(
            1793,
            "BUSINESS_ERROR_01793", 
            "�R�[�X�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01794 = errorMgr.defineErrorInfo(
            1794,
            "BUSINESS_ERROR_01794", 
            "�����ԍ������w��ł��B");

    /**
     * �\�����ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01795 = errorMgr.defineErrorInfo(
            1795,
            "BUSINESS_ERROR_01795", 
            "�\�����ԁi���j�ƕ\�����ԁi���j�͂����ꂩ�����͂���鎞�A�c���������͂����͂��ł��B");

    /**
     * ������ӎ���񓚐������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01796 = errorMgr.defineErrorInfo(
            1796,
            "BUSINESS_ERROR_01796", 
            "������ӎ���ɑ΂���񓚂�����������܂���B");

    /**
     * 2�d���M�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01799 = errorMgr.defineErrorInfo(
            1799,
            "BUSINESS_ERROR_01799", 
            "2�d���M�G���[�B");

    /**
     * ���̑���FX�V�X�e���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01800 = errorMgr.defineErrorInfo(
            1800,
            "BUSINESS_ERROR_01800", 
            "���̑���FX�V�X�e���G���[�B");

    /**
     * ��t���ԊO�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01801 = errorMgr.defineErrorInfo(
            1801,
            "BUSINESS_ERROR_01801", 
            "��t���ԊO�G���[�B");

    /**
     * �ʐM�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01802 = errorMgr.defineErrorInfo(
            1802,
            "BUSINESS_ERROR_01802", 
            "�ʐM�G���[�B");

    /**
     * �c���s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01803 = errorMgr.defineErrorInfo(
            1803,
            "BUSINESS_ERROR_01803", 
            "�c���s���G���[�B");

    /**
     * �����J�ݒ��`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01807 = errorMgr.defineErrorInfo(
            1807,
            "BUSINESS_ERROR_01807", 
            "�����J�ݒ��`�F�b�N�G���[�B");

    /**
     * FX�ڋq���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01808 = errorMgr.defineErrorInfo(
            1808,
            "BUSINESS_ERROR_01808", 
            "FX�ڋq���擾�ł��܂���B");

    /**
     * �⌇�҂̃f�[�^�����݂��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01809 = errorMgr.defineErrorInfo(
            1809,
            "BUSINESS_ERROR_01809", 
            "�⌇�҂̃f�[�^�����݂��܂���B");

    /**
     * �ڋq�ɊY������IPO�\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01810 = errorMgr.defineErrorInfo(
            1810,
            "BUSINESS_ERROR_01810", 
            "IPO�\�����L���łȂ��ꍇ�AIPO�\���i�J�㒊�I�j�s�ł��B");

    /**
     * �ڋq�ɊY������IPO�\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01811 = errorMgr.defineErrorInfo(
            1811,
            "BUSINESS_ERROR_01811", 
            "���I���ʂ��w���\�����ʂ𒴂��Ă���ꍇ�AIPO�\���i�J�㒊�I�j�s�ł��B");

    /**
     * �����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01814 = errorMgr.defineErrorInfo(
            1814,
            "BUSINESS_ERROR_01814", 
            "���i�Łh�S���i�h�A�h�����h�ȊO���I������Ă���ꍇ�́A�����R�[�h�͎w��ł��܂���B");

    /**
     * �o����܂Œ����L�������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01815 = errorMgr.defineErrorInfo(
            1815,
            "BUSINESS_ERROR_01815", 
            "�o����܂Œ��������L�������̃`�F�b�N�G���[�B");

    /**
     * �A�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01816 = errorMgr.defineErrorInfo(
            1816,
            "BUSINESS_ERROR_01816", 
            "�A����񂪖��w��ł��B");

    /**
     * �A����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01817 = errorMgr.defineErrorInfo(
            1817,
            "BUSINESS_ERROR_01817", 
            "�A����ʂ����w��ł��B");

    /**
     * �A����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01818 = errorMgr.defineErrorInfo(
            1818,
            "BUSINESS_ERROR_01818", 
            "�A����ʂ����p�����ȊO�̒l�ł��B");

    /**
     * �A����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01819 = errorMgr.defineErrorInfo(
            1819,
            "BUSINESS_ERROR_01819", 
            "�A����ʂ̃T�C�Y���s���ł��B");

    /**
     * ���ʃR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01820 = errorMgr.defineErrorInfo(
            1820,
            "BUSINESS_ERROR_01820", 
            "���ʃR�[�h�̒l�����p�����ȊO�̒l�ł��B");

    /**
     * ���ʃR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01821 = errorMgr.defineErrorInfo(
            1821,
            "BUSINESS_ERROR_01821", 
            "���ʃR�[�h�̃T�C�Y���s���ł��B");

    /**
     * ��t�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01822 = errorMgr.defineErrorInfo(
            1822,
            "BUSINESS_ERROR_01822", 
            "��t�����i���j�͎�t�����i���j���傫���ł��B");

    /**
     * ���[���o�^�󋵃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01823 = errorMgr.defineErrorInfo(
            1823,
            "BUSINESS_ERROR_01823", 
            "�m�F���[���̑��M�敪���u���M����v�Ƃ��ăT�[�r�X�̒񋟂��J�n���悤�Ƃ����ꍇ�A�m�F���[�������[���e�[�u���ɓo�^����Ă��܂���B");

    /**
     * ���[���o�^�󋵃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01824 = errorMgr.defineErrorInfo(
            1824,
            "BUSINESS_ERROR_01824", 
            "�_��������[���̑��M�敪���u���M����v�Ƃ��ăT�[�r�X�̒񋟂��J�n���悤�Ƃ����ꍇ�A�_��������[�������[���e�[�u���ɓo�^����Ă��܂���B");

    /**
     * �`�F�b�N�ΏۂɊ܂܂��\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01825 = errorMgr.defineErrorInfo(
            1825,
            "BUSINESS_ERROR_01825", 
            "�\���F���͋敪�Ɨ\���F���͋敪�����̏������s���ł��B");

    /**
     * �`�F�b�N�ΏۂɊ܂܂��\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01826 = errorMgr.defineErrorInfo(
            1826,
            "BUSINESS_ERROR_01826", 
            "�\���F���͋敪�Ɨ\���F���͋敪�����̊Ԃɐ��l�ȊO�̂��̂�����܂��B");

    /**
     * �`�F�b�N�ΏۂɊ܂܂��\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01827 = errorMgr.defineErrorInfo(
            1827,
            "BUSINESS_ERROR_01827", 
            "URL�Ɋ܂܂��\���́u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�܂��B");

    /**
     * �`�F�b�N�ΏۂɊ܂܂��\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01828 = errorMgr.defineErrorInfo(
            1828,
            "BUSINESS_ERROR_01828", 
            "��QURL�Ɋ܂܂��\���́u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�܂��B");

    /**
     * �`�F�b�N�ΏۂɊ܂܂��\���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01829 = errorMgr.defineErrorInfo(
            1829,
            "BUSINESS_ERROR_01829", 
            "���M�p�����[�^�ꗗ�Ɋ܂܂��\���́u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�܂��B");

    /**
     * �Í����ڋq�R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01830 = errorMgr.defineErrorInfo(
            1830,
            "BUSINESS_ERROR_01830", 
            "���M�p�����[�^�ɈÍ����ڋq�R�[�h�̎w�肪����܂���B");

    /**
     * �n�b�V���l�̌����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01831 = errorMgr.defineErrorInfo(
            1831,
            "BUSINESS_ERROR_01831", 
            "�n�b�V���v�Z�p������̎w��Ɍ�肪����܂��B");

    /**
     * �n�b�V���l�̌����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01832 = errorMgr.defineErrorInfo(
            1832,
            "BUSINESS_ERROR_01832", 
            "�n�b�V���v�Z�p������̗L���v�f�����s���ł��B");

    /**
     * ��QURL�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01833 = errorMgr.defineErrorInfo(
            1833,
            "BUSINESS_ERROR_01833", 
            "��2URL�������͂ł��B");

    /**
     * �o�^�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01834 = errorMgr.defineErrorInfo(
            1834,
            "BUSINESS_ERROR_01834", 
            "�ύX�ڋq�ꗗ�̓o�^�敪�����w��ł��B");

    /**
     * �o�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01835 = errorMgr.defineErrorInfo(
            1835,
            "BUSINESS_ERROR_01835", 
            "�o�����̎w��Ɍ�肪����܂��B");

    /**
     * �o�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01836 = errorMgr.defineErrorInfo(
            1836,
            "BUSINESS_ERROR_01836", 
            "���ݎ�����16:00�ȏ�̏ꍇ�A�������̗��c�Ɠ��͒������e.�o�����ȏ�̏ꍇ�A�G���[�ɂȂ�܂��B");

    /**
     * �M�p�����敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01837 = errorMgr.defineErrorInfo(
            1837,
            "BUSINESS_ERROR_01837", 
            "�M�p�����敪�����w��ł��B");

    /**
     * �M�p�����敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01838 = errorMgr.defineErrorInfo(
            1838,
            "BUSINESS_ERROR_01838", 
            "�M�p�����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �敨OP�����敪�i��؁j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01839 = errorMgr.defineErrorInfo(
            1839,
            "BUSINESS_ERROR_01839", 
            "�敨OP�����敪�i��؁j�����w��ł��B");

    /**
     * �敨OP�����敪�i��؁j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01840 = errorMgr.defineErrorInfo(
            1840,
            "BUSINESS_ERROR_01840", 
            "�敨OP�����敪�i��؁j�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �n�b�V���v�Z�����敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01841 = errorMgr.defineErrorInfo(
            1841,
            "BUSINESS_ERROR_01841", 
            "�n�b�V���v�Z�����敪�����w��ł��B");

    /**
     * �n�b�V���v�Z�����敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01842 = errorMgr.defineErrorInfo(
            1842,
            "BUSINESS_ERROR_01842", 
            "�n�b�V���v�Z�����敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �n�b�V���v�Z�菇�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01843 = errorMgr.defineErrorInfo(
            1843,
            "BUSINESS_ERROR_01843", 
            "�n�b�V���v�Z�菇�敪�����w��ł��B");

    /**
     * �n�b�V���v�Z�菇�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01844 = errorMgr.defineErrorInfo(
            1844,
            "BUSINESS_ERROR_01844", 
            "�n�b�V���v�Z�菇�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���M���@@�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01845 = errorMgr.defineErrorInfo(
            1845,
            "BUSINESS_ERROR_01845", 
            "���M���@@�敪�����w��ł��B");

    /**
     * ���M���@@�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01846 = errorMgr.defineErrorInfo(
            1846,
            "BUSINESS_ERROR_01846", 
            "���M���@@�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���M���@@�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01847 = errorMgr.defineErrorInfo(
            1847,
            "BUSINESS_ERROR_01847", 
            "���M�p�����[�^�敪�����w��ł��B");

    /**
     * ���M�p�����[�^�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01848 = errorMgr.defineErrorInfo(
            1848,
            "BUSINESS_ERROR_01848", 
            "���M�p�����[�^�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �Í����ڋq�R�[�h�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01849 = errorMgr.defineErrorInfo(
            1849,
            "BUSINESS_ERROR_01849", 
            "�Í����ڋq�R�[�h�敪�����w��ł��B");

    /**
     * �Í����ڋq�R�[�h�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01850 = errorMgr.defineErrorInfo(
            1850,
            "BUSINESS_ERROR_01850", 
            "�Í����ڋq�R�[�h�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �n�b�V���l�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01851 = errorMgr.defineErrorInfo(
            1851,
            "BUSINESS_ERROR_01851", 
            "�n�b�V���l�ꗗ�̗��p�L�[��ʋ敪�����w��ł��B");

    /**
     * �n�b�V���l�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01852 = errorMgr.defineErrorInfo(
            1852,
            "BUSINESS_ERROR_01852", 
            "�n�b�V���l�ꗗ�̗��p�L�[�����w��ł��B");

    /**
     * ���M�p�����[�^�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01853 = errorMgr.defineErrorInfo(
            1853,
            "BUSINESS_ERROR_01853", 
            "���M�p�����[�^�ꗗ�̗��p�L�[��ʋ敪�����w��ł��B");

    /**
     * ���M�p�����[�^�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01854 = errorMgr.defineErrorInfo(
            1854,
            "BUSINESS_ERROR_01854", 
            "���M�p�����[�^�ꗗ�̗��p�L�[�����w��ł��B");

    /**
     * �n�b�V���v�Z�����敪�A�n�b�V���v�Z�菇�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01855 = errorMgr.defineErrorInfo(
            1855,
            "BUSINESS_ERROR_01855", 
            "�n�b�V���v�Z�����敪���邢�̓n�b�V���v�Z�菇�敪�̎w��Ɍ�肪����܂��B");

    /**
     * �n�b�V���l�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01856 = errorMgr.defineErrorInfo(
            1856,
            "BUSINESS_ERROR_01856", 
            "�n�b�V���v�Z�p�����񂪖����͂ł��B");

    /**
     * �n�b�V���l�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01857 = errorMgr.defineErrorInfo(
            1857,
            "BUSINESS_ERROR_01857", 
            "�n�b�V���v�Z�p������̑S�Ă������ƂȂ��Ă��܂��B");

    /**
     * ���M�p�����[�^�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01858 = errorMgr.defineErrorInfo(
            1858,
            "BUSINESS_ERROR_01858", 
            "���M�p�����[�^�������͂ł��B");

    /**
     * ���M�p�����[�^�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01859 = errorMgr.defineErrorInfo(
            1859,
            "BUSINESS_ERROR_01859", 
            "���M�p�����[�^�̎w��Ɍ�肪����܂��B");

    /**
     * ���ӏ������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01860 = errorMgr.defineErrorInfo(
            1860,
            "BUSINESS_ERROR_01860", 
            "�X�e�[�^�X���u��~���v�ł͂Ȃ��̏ꍇ�́A���ӏ����������w��ł��B");

    /**
     * ��W�g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01861 = errorMgr.defineErrorInfo(
            1861,
            "BUSINESS_ERROR_01861", 
            "��W���ԏ��̕�W�g�̃T�C�Y���s���ł��B");

    /**
     * �⑫���̓`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01862 = errorMgr.defineErrorInfo(
            1862,
            "BUSINESS_ERROR_01862", 
            "���I�ݒ肪�g�L�h�̏ꍇ�́A���D�z�����w��ł��B");

    /**
     * �O���V�X�e������t�\���ԊO�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01863 = errorMgr.defineErrorInfo(
            1863,
            "BUSINESS_ERROR_01863", 
            "�O���V�X�e������t�\���ԊO�ł��B");

    /**
     * FX�����J�݃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01865 = errorMgr.defineErrorInfo(
            1865,
            "BUSINESS_ERROR_01865", 
            "�ב֕ۏ؋����������ɊJ�ݍς݂ł��B");

    /**
     * FX�����J�݃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01866 = errorMgr.defineErrorInfo(
            1866,
            "BUSINESS_ERROR_01866", 
            "�ב֕ۏ؋����������J�݂ł��B");

    /**
     * FX�����J�݃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01867 = errorMgr.defineErrorInfo(
            1867,
            "BUSINESS_ERROR_01867", 
            "�ב֕ۏ؋�����������s�̏�Ԃł��B");

    /**
     * MRF�����J�݃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01868 = errorMgr.defineErrorInfo(
            1868,
            "BUSINESS_ERROR_01868", 
            "MRF�������J�ݍς݂ł��B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01869 = errorMgr.defineErrorInfo(
            1869,
            "BUSINESS_ERROR_01869", 
            "�����`�F�b�N�G���[�B");

    /**
     * CSV�t�@@�C���s�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01870 = errorMgr.defineErrorInfo(
            1870,
            "BUSINESS_ERROR_01870", 
            "CSV�t�@@�C���s�̗v�f�����O�ł��B");

    /**
     * CSV�t�@@�C���s�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01871 = errorMgr.defineErrorInfo(
            1871,
            "BUSINESS_ERROR_01871", 
            "CSV�t�@@�C���s�͍ő又���������z���Ă��܂��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01872 = errorMgr.defineErrorInfo(
            1872,
            "BUSINESS_ERROR_01872", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�t�w�l�p���������P�����w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01873 = errorMgr.defineErrorInfo(
            1873,
            "BUSINESS_ERROR_01873", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�t�w�l�p�����������Z�q���w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01874 = errorMgr.defineErrorInfo(
            1874,
            "BUSINESS_ERROR_01874", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p���������P�����w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01875 = errorMgr.defineErrorInfo(
            1875,
            "BUSINESS_ERROR_01875", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p�����������Z�q���w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01876 = errorMgr.defineErrorInfo(
            1876,
            "BUSINESS_ERROR_01876", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p�����P���敪���w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01877 = errorMgr.defineErrorInfo(
            1877,
            "BUSINESS_ERROR_01877", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p�����P�����w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01878 = errorMgr.defineErrorInfo(
            1878,
            "BUSINESS_ERROR_01878", 
            "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p���������P�����w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01879 = errorMgr.defineErrorInfo(
            1879,
            "BUSINESS_ERROR_01879", 
            "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p�����������Z�q���w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01880 = errorMgr.defineErrorInfo(
            1880,
            "BUSINESS_ERROR_01880", 
            "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p�����P���敪���w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01881 = errorMgr.defineErrorInfo(
            1881,
            "BUSINESS_ERROR_01881", 
            "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p�����P�����w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01882 = errorMgr.defineErrorInfo(
            1882,
            "BUSINESS_ERROR_01882", 
            "���������敪���g2�FW�w�l�h�̏ꍇ�́A�t�w�l�p���������P�����w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01883 = errorMgr.defineErrorInfo(
            1883,
            "BUSINESS_ERROR_01883", 
            "���������敪���g2�FW�w�l�h�̏ꍇ�́A�t�w�l�p�����������Z�q���w��s�ł��B");

    /**
     * �J�e�S���[�ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01884 = errorMgr.defineErrorInfo(
            1884,
            "BUSINESS_ERROR_01884", 
            "�J�e�S���[�ԍ��������ȊO�̒l�ł��B");

    /**
     * �\��ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01885 = errorMgr.defineErrorInfo(
            1885,
            "BUSINESS_ERROR_01885", 
            "�\��ID�������ȊO�̒l�ł��B");

    /**
     * �J�e�S���[���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01886 = errorMgr.defineErrorInfo(
            1886,
            "BUSINESS_ERROR_01886", 
            "�J�e�S���[�������w��ł��B");

    /**
     * �i�i���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01887 = errorMgr.defineErrorInfo(
            1887,
            "BUSINESS_ERROR_01887", 
            "�i�i�������w��ł��B");

    /**
     * ��p�]���ቺ�������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01888 = errorMgr.defineErrorInfo(
            1888,
            "BUSINESS_ERROR_01888", 
            "��p�]���ቺ���͐�������͂��Ă��������B");

    /**
     * ��p�]���ቺ���͈̓`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01889 = errorMgr.defineErrorInfo(
            1889,
            "BUSINESS_ERROR_01889", 
            "��p�]���ቺ����0�ȏ�100�ȉ��œ��͂��Ă��������B");

    /**
     * ��p�]���ቺ�������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01890 = errorMgr.defineErrorInfo(
            1890,
            "BUSINESS_ERROR_01890", 
            "��p�]���ቺ���͏����_��2�ʂ܂łœ��͂��Ă��������B");

    /**
     * �a��،��]�����敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01891 = errorMgr.defineErrorInfo(
            1891,
            "BUSINESS_ERROR_01891", 
            "�a��،��]�����敪�����w��(null)�ł��B");

    /**
     * �a��،��]�����ύX�ڋq�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01892 = errorMgr.defineErrorInfo(
            1892,
            "BUSINESS_ERROR_01892", 
            "�M�p�ڋq�͗a��،��]�����ɕύX�ł��܂���B");

    /**
     * �ڋq�]�͏���ID���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01893 = errorMgr.defineErrorInfo(
            1893,
            "BUSINESS_ERROR_01893", 
            "�ڋq�]�͏���ID�����w��(null)�܂��͕s���Ȓl�ł��B");

    /**
     * �����~�敪ID���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01894 = errorMgr.defineErrorInfo(
            1894,
            "BUSINESS_ERROR_01894", 
            "�����~�敪�����w��(null)�ł��B");

    /**
     * �M�p�V�K���]�͋敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01895 = errorMgr.defineErrorInfo(
            1895,
            "BUSINESS_ERROR_01895", 
            "�M�p�V�K���]�͋敪�����w��(null)�ł��B");

    /**
     * �敨OP�V�K���]�͋敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01896 = errorMgr.defineErrorInfo(
            1896,
            "BUSINESS_ERROR_01896", 
            "�敨OP�V�K���]�͋敪�����w��(null)�ł��B");

    /**
     * �o���]�͋敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01897 = errorMgr.defineErrorInfo(
            1897,
            "BUSINESS_ERROR_01897", 
            "�o���]�͋敪�����w��(null)�ł��B");

    /**
     * ���̑����i���t�]�͋敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01898 = errorMgr.defineErrorInfo(
            1898,
            "BUSINESS_ERROR_01898", 
            "���̑����i���t�]�͋敪�����w��(null)�ł��B");

    /**
     * �������ԃ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01899 = errorMgr.defineErrorInfo(
            1899,
            "BUSINESS_ERROR_01899", 
            "�������Ԃ̎w�肪�s���ł��B");

    /**
     * ��~�J�n�����w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01900 = errorMgr.defineErrorInfo(
            1900,
            "BUSINESS_ERROR_01900", 
            "��~�J�n�������w��(null)�ł��B");

    /**
     * ��~�I�������w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01901 = errorMgr.defineErrorInfo(
            1901,
            "BUSINESS_ERROR_01901", 
            "��~�I���������w��(null)�ł��B");

    /**
     * ��~���ԃ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01902 = errorMgr.defineErrorInfo(
            1902,
            "BUSINESS_ERROR_01902", 
            "��~���Ԃ̎w�肪�s���ł��B");

    /**
     * �ۏ؋������U�֒�~ID�����`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01903 = errorMgr.defineErrorInfo(
            1903,
            "BUSINESS_ERROR_01903", 
            "�ۏ؋������U�֒�~ID�����w��(null)�܂��͑��݂��Ȃ��l�ł��B");

    /**
     * �ۏ؋������U�֒�~�o�^�σ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01904 = errorMgr.defineErrorInfo(
            1904,
            "BUSINESS_ERROR_01904", 
            "���ɕۏ؋������U�֒�~�o�^�ςł���B");

    /**
     * �\�����I�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01905 = errorMgr.defineErrorInfo(
            1905,
            "BUSINESS_ERROR_01905", 
            "�\�����I�敪�����w��ł��B");

    /**
     * ���p���ԗ������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01906 = errorMgr.defineErrorInfo(
            1906,
            "BUSINESS_ERROR_01906", 
            "�񋟌`���������񋟂̏ꍇ�́A���p���ԗ�����񂪎w��s�ł��B");

    /**
     * ���p���ԒP�ʋ敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01908 = errorMgr.defineErrorInfo(
            1908,
            "BUSINESS_ERROR_01908", 
            "�񋟌`���������񋟂̏ꍇ�́A���p���ԒP�ʋ敪���w��s�ł��B");

    /**
     * ���p���Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01909 = errorMgr.defineErrorInfo(
            1909,
            "BUSINESS_ERROR_01909", 
            "�񋟌`���������񋟂̏ꍇ�́A���p���Ԃ��w��s�ł��B");

    /**
     * �p�X���[�h�P�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01910 = errorMgr.defineErrorInfo(
            1910,
            "BUSINESS_ERROR_01910", 
            "�p�X���[�h�P�������͂ł��B");

    /**
     * �p�X���[�h�Q�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01911 = errorMgr.defineErrorInfo(
            1911,
            "BUSINESS_ERROR_01911", 
            "�p�X���[�h�Q�������͂ł��B");

    /**
     * �R�[�h�i������j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01912 = errorMgr.defineErrorInfo(
            1912,
            "BUSINESS_ERROR_01912", 
            "���҃R�[�h�i������j�̒������s���ł��B");

    /**
     * �R�[�h�i������j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01913 = errorMgr.defineErrorInfo(
            1913,
            "BUSINESS_ERROR_01913", 
            "���҃R�[�h�i������j�̕����킪�s���ł��B");

    /**
     * �R�[�h�i������j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01914 = errorMgr.defineErrorInfo(
            1914,
            "BUSINESS_ERROR_01914", 
            "�R�[�h�i������j�̃`�F�b�N�����ɒ�`����Ă��Ȃ����̂��w�肳�ꂽ�B");

    /**
     * �R�[�h�i������j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01915 = errorMgr.defineErrorInfo(
            1915,
            "BUSINESS_ERROR_01915", 
            "�p�X���[�h�i������j�̒������s���ł��B");

    /**
     * �R�[�h�i������j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01916 = errorMgr.defineErrorInfo(
            1916,
            "BUSINESS_ERROR_01916", 
            "�p�X���[�h�i������j�̕����킪�s���ł��B");

    /**
     * �R�[�h�i������j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01917 = errorMgr.defineErrorInfo(
            1917,
            "BUSINESS_ERROR_01917", 
            "�Ǘ��҃R�[�h�i������j�̒������s���ł��B");

    /**
     * �R�[�h�i������j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01918 = errorMgr.defineErrorInfo(
            1918,
            "BUSINESS_ERROR_01918", 
            "�Ǘ��҃R�[�h�i������j�̕����킪�s���ł��B");

    /**
     * �ۗL���YID�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01919 = errorMgr.defineErrorInfo(
            1919,
            "BUSINESS_ERROR_01919", 
            "�ۗL���YID�����w��(null)�ł��B");

    /**
     * �ύX��뉿�P���`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01920 = errorMgr.defineErrorInfo(
            1920,
            "BUSINESS_ERROR_01920", 
            "���͂��ꂽ�뉿�P�����s���Ȓl�ł��B");

    /**
     * �v�Z��뉿�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01921 = errorMgr.defineErrorInfo(
            1921,
            "BUSINESS_ERROR_01921", 
            "���͂����뉿�P���̒l���傫�����܂��B(�v�Z��̕뉿�̌�����12���ȏ�)");

    /**
     * �t�w�l�p�v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01922 = errorMgr.defineErrorInfo(
            1922,
            "BUSINESS_ERROR_01922", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�t�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");

    /**
     * �t�w�l�p�v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01923 = errorMgr.defineErrorInfo(
            1923,
            "BUSINESS_ERROR_01923", 
            "���������敪���g2�FW�w�l�h�̏ꍇ�́A�t�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");

    /**
     * �v�w�l�p�v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01924 = errorMgr.defineErrorInfo(
            1924,
            "BUSINESS_ERROR_01924", 
            "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�v�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");

    /**
     * �v�w�l�p�v���~�A���^�����Y���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01925 = errorMgr.defineErrorInfo(
            1925,
            "BUSINESS_ERROR_01925", 
            "���������敪���g1�F�t�w�l�h�̏ꍇ�́A�v�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");

    /**
     * ���ꒊ�I���ւ̏d���\���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01926 = errorMgr.defineErrorInfo(
            1926,
            "BUSINESS_ERROR_01926", 
            "����̒��I���i�����\�����ԁj�ɓ�ڂ̐\�������悤�Ƃ��Ă���ꍇ�̃G���[�B");

    /**
     * ���񋟃T�[�r�X�w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01927 = errorMgr.defineErrorInfo(
            1927,
            "BUSINESS_ERROR_01927", 
            "�T�[�r�X����~���̎��̃G���[�i�T�[�r�X�N���j�B");

    /**
     * ��K���`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01928 = errorMgr.defineErrorInfo(
            1928,
            "BUSINESS_ERROR_01928", 
            "��K���`�F�b�N�G���[�B");

    /**
     * ���t�a����s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01929 = errorMgr.defineErrorInfo(
            1929,
            "BUSINESS_ERROR_01929", 
            "���t�a����s���B");

    /**
     * ���t�a����s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01930 = errorMgr.defineErrorInfo(
            1930,
            "BUSINESS_ERROR_01930", 
            "���t�a����s���B");

    /**
     * �ۗL���Y�c���ʃ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01931 = errorMgr.defineErrorInfo(
            1931,
            "BUSINESS_ERROR_01931", 
            "�ۗL���Y�c���ʃ`�F�b�N�G���[�B");

    /**
     * ���������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01932 = errorMgr.defineErrorInfo(
            1932,
            "BUSINESS_ERROR_01932", 
            "�������������ω\���������𒴂��܂����B");

    /**
     * ����O�������������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01933 = errorMgr.defineErrorInfo(
            1933,
            "BUSINESS_ERROR_01933", 
            "����O���������͒����ł��܂���B");

    /**
     * �����c���ʃ`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01934 = errorMgr.defineErrorInfo(
            1934,
            "BUSINESS_ERROR_01934", 
            "�����c���ʃ`�F�b�N�G���[�B");

    /**
     * �V�K���a����s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01935 = errorMgr.defineErrorInfo(
            1935,
            "BUSINESS_ERROR_01935", 
            "�V�K���a����s���B");

    /**
     * �ڋq�R�[�h�w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01936 = errorMgr.defineErrorInfo(
            1936,
            "BUSINESS_ERROR_01936", 
            "�w�肳�ꂽ�Ώیڋq��ʂ̏ꍇ�A�ڋq�R�[�h�͓��͂ł��܂���B");

    /**
     * �U������Z�@@�ւ��o�^����Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01937 = errorMgr.defineErrorInfo(
            1937,
            "BUSINESS_ERROR_01937", 
            "�U������Z�@@�ւ��o�^����Ă��܂���B");

    /**
     * ������Ԃ̃`�F�b�N �B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01938 = errorMgr.defineErrorInfo(
            1938,
            "BUSINESS_ERROR_01938", 
            "������Ԃ���t�ςłȂ��A���A���敪�������l�łȂ��̏ꍇ�́A����������s�ł��B");

    /**
     * �O���U�֎���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01940 = errorMgr.defineErrorInfo(
            1940,
            "BUSINESS_ERROR_01940", 
            "�������̐U�֒��������݂���̂ŁA����ł��܂���B");

    /**
     * �O�������J�݉\�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01941 = errorMgr.defineErrorInfo(
            1941,
            "BUSINESS_ERROR_01941", 
            "�O�����������͊��ɊJ�݂���Ă��܂��B");

    /**
     * �O�������J�݉\�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01943 = errorMgr.defineErrorInfo(
            1943,
            "BUSINESS_ERROR_01943", 
            "�ڋq���̃��[���A�h���X���o�^����Ă��܂���B");

    /**
     * �O���U�։\�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01944 = errorMgr.defineErrorInfo(
            1944,
            "BUSINESS_ERROR_01944", 
            "�O�������������J�݂���Ă��܂���B");

    /**
     * ����U�ւ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01946 = errorMgr.defineErrorInfo(
            1946,
            "BUSINESS_ERROR_01946", 
            "����U�ւ̏ꍇ�A�U�֋��z���������z�ɒB���Ă��܂���B");

    /**
     * ���l���ڂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01947 = errorMgr.defineErrorInfo(
            1947,
            "BUSINESS_ERROR_01947", 
            "���͂̐��l���ڂ����p�����ȊO�̒l�ł��B");

    /**
     * �ꗗ�ɕ\������f�[�^������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01948 = errorMgr.defineErrorInfo(
            1948,
            "BUSINESS_ERROR_01948", 
            "�ꗗ�ɕ\������f�[�^������܂���B");

    /**
     * �O�������J�ݎ���ɑ΂���񓚂̐������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01949 = errorMgr.defineErrorInfo(
            1949,
            "BUSINESS_ERROR_01949", 
            "���ӂ���ĂȂ����₪����܂��B");

    /**
     * �_�E�����[�h�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01950 = errorMgr.defineErrorInfo(
            1950,
            "BUSINESS_ERROR_01950", 
            "�_�E�����[�h�����������͂ł��B");

    /**
     * �_�E�����[�h�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01951 = errorMgr.defineErrorInfo(
            1951,
            "BUSINESS_ERROR_01951", 
            "�_�E�����[�h�������s���Ȓl�ł��B");

    /**
     * �ڋqID�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01952 = errorMgr.defineErrorInfo(
            1952,
            "BUSINESS_ERROR_01952", 
            "�ڋqID�������͂ł��B");

    /**
     * �O�������ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01953 = errorMgr.defineErrorInfo(
            1953,
            "BUSINESS_ERROR_01953", 
            "�O�������ԍ��������͂ł��B");

    /**
     * �O�������ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01954 = errorMgr.defineErrorInfo(
            1954,
            "BUSINESS_ERROR_01954", 
            "�O�������ԍ����s���Ȓl�ł��B");

    /**
     * �����敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01955 = errorMgr.defineErrorInfo(
            1955,
            "BUSINESS_ERROR_01955", 
            "���̒����͎���ł��܂���B");

    /**
     * �d�q��t�\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01956 = errorMgr.defineErrorInfo(
            1956,
            "BUSINESS_ERROR_01956", 
            "�d�q��t�̐\��������Ă��܂���B");

    /**
     * �Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01957 = errorMgr.defineErrorInfo(
            1957,
            "BUSINESS_ERROR_01957", 
            "�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B");

    /**
     * �d�q���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01959 = errorMgr.defineErrorInfo(
            1959,
            "BUSINESS_ERROR_01959", 
            "�d�q���V�X�e���ďo�ŃG���[���������܂����B");

    /**
     * �\����t�敪�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01960 = errorMgr.defineErrorInfo(
            1960,
            "BUSINESS_ERROR_01960", 
            "�\����t�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����ʒm�����G���[�i�Ɩ��G���[�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01961 = errorMgr.defineErrorInfo(
            1961,
            "BUSINESS_ERROR_01961", 
            "�����ʒm���������s���܂����i�Ɩ��G���[�j�B");

    /**
     * ����ʒm�����G���[�i�Ɩ��G���[�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01962 = errorMgr.defineErrorInfo(
            1962,
            "BUSINESS_ERROR_01962", 
            "����ʒm���������s���܂����i�Ɩ��G���[�j�B");

    /**
     * �O���p�Ïؔԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01963 = errorMgr.defineErrorInfo(
            1963,
            "BUSINESS_ERROR_01963", 
            "���͂��ꂽ�O���p�Ïؔԍ��ƊO���p�Ïؔԍ��i�m�F�p�j�͈�v���Ȃ��B");

    /**
     * �ב֕ۏ؋������J�݊Ǘ��ꗗ��ʂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01964 = errorMgr.defineErrorInfo(
            1964,
            "BUSINESS_ERROR_01964", 
            "�I�����ꂽMRF�����J�݂̏����ɍ��v����f�[�^�����݂��܂���B");

    /**
     * �d�q���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01965 = errorMgr.defineErrorInfo(
            1965,
            "BUSINESS_ERROR_01965", 
            "�d�q�����o�^�G���[");

    /**
     * ��������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01966 = errorMgr.defineErrorInfo(
            1966,
            "BUSINESS_ERROR_01966", 
            "�w�肵�������͏�ꂳ��Ă���܂���B");

    /**
     * ID��null�łȂ��ꍇ�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01967 = errorMgr.defineErrorInfo(
            1967,
            "BUSINESS_ERROR_01967", 
            "ID���w��s�ł��B");

    /**
     * �d�q���`�F�b�N�t���O��null�̏ꍇ�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01968 = errorMgr.defineErrorInfo(
            1968,
            "BUSINESS_ERROR_01968", 
            "�d�q���`�F�b�N�t���O�����w��ł��B");

    /**
     * ���̃A�b�v���[�h�v���Z�X���N�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01969 = errorMgr.defineErrorInfo(
            1969,
            "BUSINESS_ERROR_01969", 
            "���̃A�b�v���[�h�v���Z�X���N�����B");

    /**
     * �|�C���g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01970 = errorMgr.defineErrorInfo(
            1970,
            "BUSINESS_ERROR_01970", 
            "�|�C���g���s�����Ă��܂��B");

    /**
     * ��p�]���ቺ���͈̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01971 = errorMgr.defineErrorInfo(
            1971,
            "BUSINESS_ERROR_01971", 
            "��p�]���ቺ���́A0����20�̊ԂŎw�肵�Ă��������B");

    /**
     * 2�d��M�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01972 = errorMgr.defineErrorInfo(
            1972,
            "BUSINESS_ERROR_01972", 
            "2�d��M�G���[�B");

    /**
     * �X���b�h�ԍ��̎w��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01974 = errorMgr.defineErrorInfo(
            1974,
            "BUSINESS_ERROR_01974", 
            "�X���b�h�ԍ��̎w��Ȃ��B");

    /**
     * �Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01975 = errorMgr.defineErrorInfo(
            1975,
            "BUSINESS_ERROR_01975", 
            "�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");

    /**
     * �f�[�^�d���G���[�i�ڋq�����ʎ����~�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01976 = errorMgr.defineErrorInfo(
            1976,
            "BUSINESS_ERROR_01976", 
            "�f�[�^�d���G���[�i�ڋq�����ʎ����~�j�B");

    /**
     * �Y���ڋq�����ʎ����~�f�[�^�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01977 = errorMgr.defineErrorInfo(
            1977,
            "BUSINESS_ERROR_01977", 
            "�Y���ڋq�����ʎ����~�f�[�^�Ȃ��B");

    /**
     * �ύX��̓o�^�l(�\��)�������́B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01978 = errorMgr.defineErrorInfo(
            1978,
            "BUSINESS_ERROR_01978", 
            "�ύX��̓o�^�l(�\��)�������́B");

    /**
     * �ύX��̓K�p����From/To�������́B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01979 = errorMgr.defineErrorInfo(
            1979,
            "BUSINESS_ERROR_01979", 
            "�ύX��̓K�p����From/To�������́B");

    /**
     * �Y������IPO�\��������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01980 = errorMgr.defineErrorInfo(
            1980,
            "BUSINESS_ERROR_01980", 
            "�Y������IPO�\��������܂���B");

    /**
     * �Y������IPO�\������������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01981 = errorMgr.defineErrorInfo(
            1981,
            "BUSINESS_ERROR_01981", 
            "�Y������IPO�\������������܂���B");

    /**
     * ������������s�̃X�P�W���[��������ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01982 = errorMgr.defineErrorInfo(
            1982,
            "BUSINESS_ERROR_01982", 
            "������������s�̃X�P�W���[��������ł��B");

    /**
     * �v�����敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01983 = errorMgr.defineErrorInfo(
            1983,
            "BUSINESS_ERROR_01983", 
            "�v�����敪������`�̒l�B");

    /**
     * �d�q���V�X�e����Q���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01984 = errorMgr.defineErrorInfo(
            1984,
            "BUSINESS_ERROR_01984", 
            "[�d�q���V�X�e����Q��]��Q�������s�B");

    /**
     * �d�q���V�X�e����Q���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01985 = errorMgr.defineErrorInfo(
            1985,
            "BUSINESS_ERROR_01985", 
            "[�d�q���V�X�e����Q��]��Q���㗝���͕s�B");

    /**
     * �\�������z�̍ő包���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01986 = errorMgr.defineErrorInfo(
            1986,
            "BUSINESS_ERROR_01986", 
            "�\�������z�̐��������ő�l�i�P�Q���j�𒴂��Ă��܂��B");

    /**
     * �ڋq�R�[�h�w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01987 = errorMgr.defineErrorInfo(
            1987,
            "BUSINESS_ERROR_01987", 
            "�ڋq�R�[�h�ɑΉ�����ڋq�͓o�^����Ă��܂���B");

    /**
     * �d�q���V�X�e���ғ����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01988 = errorMgr.defineErrorInfo(
            1988,
            "BUSINESS_ERROR_01988", 
            "[�d�q���V�X�e���ғ���]�ژ_�����{�����όڋq�̑㗝���͕s�B");

    /**
     * �T�[�r�X�o�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01989 = errorMgr.defineErrorInfo(
            1989,
            "BUSINESS_ERROR_01989", 
            "�w��̃T�[�r�X�͊��ɓo�^����Ă��܂��B");

    /**
     * ���������̓G���[(��������)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01990 = errorMgr.defineErrorInfo(
            1990,
            "BUSINESS_ERROR_01990", 
            "�������̓��͉\���𒴂��܂����B");

    /**
     * IPO�\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01991 = errorMgr.defineErrorInfo(
            1991,
            "BUSINESS_ERROR_01991", 
            "���I���ʂ��w���\�����ʂ𒴂��Ă���ꍇ�AIPO�\���i�J�㒊�I�j�s�B");

    /**
     * �w��AP�N�����i��d�N���G���[�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01992 = errorMgr.defineErrorInfo(
            1992,
            "BUSINESS_ERROR_01992", 
            "�w��AP�N�����i��d�N���G���[�j�B");

    /**
     * CSV�t�@@�C���̗v�f�����s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01993 = errorMgr.defineErrorInfo(
            1993,
            "BUSINESS_ERROR_01993", 
            "CSV�t�@@�C���̗v�f�����s���B");

    /**
     * �A�b�v���[�h�t�@@�C�����̍��ڂ̒��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01994 = errorMgr.defineErrorInfo(
            1994,
            "BUSINESS_ERROR_01994", 
            "�A�b�v���[�h�t�@@�C�����̍��ڂ̒��Ɨv�����ꂽ�����O�X���Ⴂ�܂��B");

    /**
     * �J�㒊�I�Ώێ҃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01995 = errorMgr.defineErrorInfo(
            1995,
            "BUSINESS_ERROR_01995", 
            "�J�㒊�I�Ώێ҂̌ڋq�ł͂���܂���B");

    /**
     * �������̃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01996 = errorMgr.defineErrorInfo(
            1996,
            "BUSINESS_ERROR_01996", 
            "�������ɔ��p�J�i�������܂܂�Ă��܂��B");

    /**
     * �����擾�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01997 = errorMgr.defineErrorInfo(
            1997,
            "BUSINESS_ERROR_01997", 
            "�w��̖����͐��s�����ɕK�v�Ȏs�ꎞ�����Ȃ����߁A�w�l�����̂ݎ�t�\�ł��B");

    /**
     * ���I�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01998 = errorMgr.defineErrorInfo(
            1998,
            "BUSINESS_ERROR_01998", 
            "�A�b�v���[�hCSV�ɂ��钊�I�敪�͑I�����ꂽ�敪�ƕs��v�ł��B");

    /**
     * �T�[�r�X�o�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01999 = errorMgr.defineErrorInfo(
            1999,
            "BUSINESS_ERROR_01999", 
            "���Y�T�[�r�X�ւ̐\���o�^�����ςł��B");

    /**
     * ���M�抷�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02000 = errorMgr.defineErrorInfo(
            2000,
            "BUSINESS_ERROR_02000", 
            "���t�\������0���ȉ��Ȃ̂ŏ抷���ł��܂���B");

    /**
     * ���ʃ`�F�b�N�G���[�i�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02001 = errorMgr.defineErrorInfo(
            2001,
            "BUSINESS_ERROR_02001", 
            "��������1�񂠂���̒����\�Ȑ��ʂ̏���l���z���Ă��܂��B");

    /**
     * ���ʃ`�F�b�N�G���[�i�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02002 = errorMgr.defineErrorInfo(
            2002,
            "BUSINESS_ERROR_02002", 
            "��������1�񂠂���̒����\�Ȑ��ʂ̏���l���z���Ă��܂��B");

    /**
     * ���ʃ`�F�b�N�G���[�i���ԍρj�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02003 = errorMgr.defineErrorInfo(
            2003,
            "BUSINESS_ERROR_02003", 
            "���ԍϒ���1�񂠂���̒����\�Ȑ��ʂ̏���l���z���Ă��܂��B");

    /**
     * ���ʃ`�F�b�N�G���[�i���ԍρj�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02004 = errorMgr.defineErrorInfo(
            2004,
            "BUSINESS_ERROR_02004", 
            "���ԍϒ���1�񂠂���̒����\�Ȑ��ʂ̏���l���z���Ă��܂��B");

    /**
     * ���ʃ`�F�b�N�G���[�i�������j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02005 = errorMgr.defineErrorInfo(
            2005,
            "BUSINESS_ERROR_02005", 
            "���������ʂ̏���l�𒴂��Ă��܂��B");

    /**
     * ���ʃ`�F�b�N�G���[�i�����ʁj�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02006 = errorMgr.defineErrorInfo(
            2006,
            "BUSINESS_ERROR_02006", 
            "�����ʐ��ʂ̏���l�𒴂��Ă��܂��B");

    /**
     * �ڋq�R�[�h�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02007 = errorMgr.defineErrorInfo(
            2007,
            "BUSINESS_ERROR_02007", 
            "�ڋq�R�[�h�i���j�������͂ł��B");

    /**
     * �ڋq�R�[�h�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02008 = errorMgr.defineErrorInfo(
            2008,
            "BUSINESS_ERROR_02008", 
            "�ڋq�R�[�h�i���j�������͂ł��B");

    /**
     * ���M�t���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02009 = errorMgr.defineErrorInfo(
            2009,
            "BUSINESS_ERROR_02009", 
            "���M�t���O���s���Ȓl�ł��B");

    /**
     * �����`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02010 = errorMgr.defineErrorInfo(
            2010,
            "BUSINESS_ERROR_02010", 
            "���ꔭ�����Ɍ����w����Ƌ��z�w����̗����̒����͂ł��܂���B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02011 = errorMgr.defineErrorInfo(
            2011,
            "BUSINESS_ERROR_02011", 
            "�����h�c�ɊY�����钍���P�ʂ����݂��܂���B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02012 = errorMgr.defineErrorInfo(
            2012,
            "BUSINESS_ERROR_02012", 
            "�����h�c�ɊY�����钍���P�ʂ��Q���ȏ゠�����̂ŁA�G���[�ɂȂ�܂��B");

    /**
     * ���t�בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02013 = errorMgr.defineErrorInfo(
            2013,
            "BUSINESS_ERROR_02013", 
            "���t�בփ��[�g�����l�ȊO�̒l�ł��B");

    /**
     * ���t��בփG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02014 = errorMgr.defineErrorInfo(
            2014,
            "BUSINESS_ERROR_02014", 
            "���t��בփG���[�B");

    /**
     * ���t���בփG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02015 = errorMgr.defineErrorInfo(
            2015,
            "BUSINESS_ERROR_02015", 
            "���t���בփG���[�B");

    /**
     * ���t�בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02016 = errorMgr.defineErrorInfo(
            2016,
            "BUSINESS_ERROR_02016", 
            "���t�בփ��[�g�����l�ȊO�̒l�ł��B");

    /**
     * ���t��בփG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02017 = errorMgr.defineErrorInfo(
            2017,
            "BUSINESS_ERROR_02017", 
            "���t��בփG���[�B");

    /**
     * ���t���בփG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02018 = errorMgr.defineErrorInfo(
            2018,
            "BUSINESS_ERROR_02018", 
            "���t���בփG���[�B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02019 = errorMgr.defineErrorInfo(
            2019,
            "BUSINESS_ERROR_02019", 
            "�������͉c�Ɠ��ł͂���܂���B");

    /**
     * �ύX��敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02020 = errorMgr.defineErrorInfo(
            2020,
            "BUSINESS_ERROR_02020", 
            "���͂��ꂽ�ύX��敪���s���ȃR�[�h�l�ł��B");

    /**
     * ���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02021 = errorMgr.defineErrorInfo(
            2021,
            "BUSINESS_ERROR_02021", 
            "���P���������͂ł��B");

    /**
     * ���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02022 = errorMgr.defineErrorInfo(
            2022,
            "BUSINESS_ERROR_02022", 
            "���P�������l�ȊO�̒l�ł��B");

    /**
     * ���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02023 = errorMgr.defineErrorInfo(
            2023,
            "BUSINESS_ERROR_02023", 
            "���P����0�ȉ��̒l�ł��B");

    /**
     * ���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02024 = errorMgr.defineErrorInfo(
            2024,
            "BUSINESS_ERROR_02024", 
            "���P���͐������U���C�������T���͈̔͊O�ł��B");

    /**
     * ��萔�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02025 = errorMgr.defineErrorInfo(
            2025,
            "BUSINESS_ERROR_02025", 
            "��萔�ʂ������͂ł��B");

    /**
     * ��萔�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02026 = errorMgr.defineErrorInfo(
            2026,
            "BUSINESS_ERROR_02026", 
            "��萔�ʂ�9���ȓ��̐����l�ł͂���܂���B");

    /**
     * �N���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02027 = errorMgr.defineErrorInfo(
            2027,
            "BUSINESS_ERROR_02027", 
            "�N���������͂ł��B");

    /**
     * �N���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02028 = errorMgr.defineErrorInfo(
            2028,
            "BUSINESS_ERROR_02028", 
            "�N����6���ȊO�ł��B");

    /**
     * �N���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02029 = errorMgr.defineErrorInfo(
            2029,
            "BUSINESS_ERROR_02029", 
            "�N�������t�Ƃ��ėL�肦�Ȃ��l�ł��B");

    /**
     * �R�X�g�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02030 = errorMgr.defineErrorInfo(
            2030,
            "BUSINESS_ERROR_02030", 
            "�R�X�g�敪���s���Ȓl�ł��B");

    /**
     * �o���I�����{�ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02031 = errorMgr.defineErrorInfo(
            2031,
            "BUSINESS_ERROR_02031", 
            "�o���I�����{�ꗗ�����݂��܂���B");

    /**
     * �^�p�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02032 = errorMgr.defineErrorInfo(
            2032,
            "BUSINESS_ERROR_02032", 
            "�^�p�R�[�h�������͂ł��B");

    /**
     * �^�p�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02033 = errorMgr.defineErrorInfo(
            2033,
            "BUSINESS_ERROR_02033", 
            "�^�p�R�[�h���V���̔��p�p�����ł͂���܂���B");

    /**
     * �^�p�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02034 = errorMgr.defineErrorInfo(
            2034,
            "BUSINESS_ERROR_02034", 
            "�^�p�R�[�h�̍�2byte���hNW�h�ł͂���܂���B");

    /**
     * �^�p�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02035 = errorMgr.defineErrorInfo(
            2035,
            "BUSINESS_ERROR_02035", 
            "�^�p�R�[�h�̉E5byte�������ł͂���܂���B");

    /**
     * ���בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02036 = errorMgr.defineErrorInfo(
            2036,
            "BUSINESS_ERROR_02036", 
            "���בփ��[�g�����w��ł��B");

    /**
     * ���בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02037 = errorMgr.defineErrorInfo(
            2037,
            "BUSINESS_ERROR_02037", 
            "���בփ��[�g�̗L���������A�������R���C�������S���͈̔͊O�ł��B");

    /**
     * �o�����ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02038 = errorMgr.defineErrorInfo(
            2038,
            "BUSINESS_ERROR_02038", 
            "�o�����ꗗ�������͂ł��B");

    /**
     * ���ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02039 = errorMgr.defineErrorInfo(
            2039,
            "BUSINESS_ERROR_02039", 
            "���ԍ��������͂ł��B");

    /**
     * ���ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02040 = errorMgr.defineErrorInfo(
            2040,
            "BUSINESS_ERROR_02040", 
            "���ԍ����L������3���ȓ��̐��̐����l�ł͂���܂���B");

    /**
     * ���h�c�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02041 = errorMgr.defineErrorInfo(
            2041,
            "BUSINESS_ERROR_02041", 
            "���h�c�������͂ł��B");

    /**
     * ���h�c�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02042 = errorMgr.defineErrorInfo(
            2042,
            "BUSINESS_ERROR_02042", 
            "���h�c�����l�ȊO�̒l�ł��B");

    /**
     * ������t����ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02043 = errorMgr.defineErrorInfo(
            2043,
            "BUSINESS_ERROR_02043", 
            "������t����ꗗ�������͂ł��B");

    /**
     * �s�ꖢ�I���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02045 = errorMgr.defineErrorInfo(
            2045,
            "BUSINESS_ERROR_02045", 
            "�s�ꖢ�I���G���[�B");

    /**
     * �s��R�[�h���ݒ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02046 = errorMgr.defineErrorInfo(
            2046,
            "BUSINESS_ERROR_02046", 
            "�s��R�[�h���ݒ�G���[�B");

    /**
     * �����������ݒ�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02047 = errorMgr.defineErrorInfo(
            2047,
            "BUSINESS_ERROR_02047", 
            "�����������ݒ�G���[�B");

    /**
     * �G���[�����Ώۋ@@�\�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02048 = errorMgr.defineErrorInfo(
            2048,
            "BUSINESS_ERROR_02048", 
            "�G���[�����Ώۋ@@�\�敪�������͂ł��B");

    /**
     * �G���[�����Ώۋ@@�\�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02049 = errorMgr.defineErrorInfo(
            2049,
            "BUSINESS_ERROR_02049", 
            "�G���[�����Ώۋ@@�\�敪���s���Ȓl�ł��B");

    /**
     * �������i�����j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02050 = errorMgr.defineErrorInfo(
            2050,
            "BUSINESS_ERROR_02050", 
            "�������i�����j�̃o�C�g�����s���ł��B");

    /**
     * ���t��~�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02051 = errorMgr.defineErrorInfo(
            2051,
            "BUSINESS_ERROR_02051", 
            "���t��~�敪�̒l���s���ł��B");

    /**
     * ���t��~�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02052 = errorMgr.defineErrorInfo(
            2052,
            "BUSINESS_ERROR_02052", 
            "���t��~�敪�̒l���s���ł��B");

    /**
     * ���n�����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02053 = errorMgr.defineErrorInfo(
            2053,
            "BUSINESS_ERROR_02053", 
            "���n�����R�[�h�����w��ł��B");

    /**
     * ���n�����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02054 = errorMgr.defineErrorInfo(
            2054,
            "BUSINESS_ERROR_02054", 
            "���n�����R�[�h�̓��͉\���𒴂��܂����B");

    /**
     * ���n�����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02055 = errorMgr.defineErrorInfo(
            2055,
            "BUSINESS_ERROR_02055", 
            "���n�����R�[�h�����l�ȊO�̒l�ł��B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02056 = errorMgr.defineErrorInfo(
            2056,
            "BUSINESS_ERROR_02056", 
            "���t�P�ʂ������͂ł��B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02057 = errorMgr.defineErrorInfo(
            2057,
            "BUSINESS_ERROR_02057", 
            "���t�P�ʂ����l�ȊO�̒l�ł��B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02058 = errorMgr.defineErrorInfo(
            2058,
            "BUSINESS_ERROR_02058", 
            "���t�P�ʂ̓��͉\���𒴂��܂����B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02059 = errorMgr.defineErrorInfo(
            2059,
            "BUSINESS_ERROR_02059", 
            "���t�P�ʂ��}�C�i�X�̒l�ł��B");

    /**
     * �Œᔃ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02060 = errorMgr.defineErrorInfo(
            2060,
            "BUSINESS_ERROR_02060", 
            "�Œᔃ�t�P�ʂ������͂ł��B");

    /**
     * �Œᔃ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02061 = errorMgr.defineErrorInfo(
            2061,
            "BUSINESS_ERROR_02061", 
            "�Œᔃ�t�P�ʂ����l�ȊO�̒l�ł��B");

    /**
     * �Œᔃ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02062 = errorMgr.defineErrorInfo(
            2062,
            "BUSINESS_ERROR_02062", 
            "�Œᔃ�t�P�ʂ̓��͉\���𒴂��܂����B");

    /**
     * �Œᔃ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02063 = errorMgr.defineErrorInfo(
            2063,
            "BUSINESS_ERROR_02063", 
            "�Œᔃ�t�P�ʂ��}�C�i�X�̒l�ł��B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02064 = errorMgr.defineErrorInfo(
            2064,
            "BUSINESS_ERROR_02064", 
            "���t�P�ʂ������͂ł��B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02065 = errorMgr.defineErrorInfo(
            2065,
            "BUSINESS_ERROR_02065", 
            "���t�P�ʂ����l�ȊO�̒l�ł��B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02066 = errorMgr.defineErrorInfo(
            2066,
            "BUSINESS_ERROR_02066", 
            "���t�P�ʂ̓��͉\���𒴂��܂����B");

    /**
     * ���t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02067 = errorMgr.defineErrorInfo(
            2067,
            "BUSINESS_ERROR_02067", 
            "���t�P�ʂ��}�C�i�X�̒l�ł��B");

    /**
     * �Œᔄ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02068 = errorMgr.defineErrorInfo(
            2068,
            "BUSINESS_ERROR_02068", 
            "�Œᔄ�t�P�ʂ������͂ł��B");

    /**
     * �Œᔄ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02069 = errorMgr.defineErrorInfo(
            2069,
            "BUSINESS_ERROR_02069", 
            "�Œᔄ�t�P�ʂ����l�ȊO�̒l�ł��B");

    /**
     * �Œᔄ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02070 = errorMgr.defineErrorInfo(
            2070,
            "BUSINESS_ERROR_02070", 
            "�Œᔄ�t�P�ʂ̓��͉\���𒴂��܂����B");

    /**
     * �Œᔄ�t�P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02071 = errorMgr.defineErrorInfo(
            2071,
            "BUSINESS_ERROR_02071", 
            "�Œᔄ�t�P�ʂ��}�C�i�X�̒l�ł��B");

    /**
     * �����͏��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02072 = errorMgr.defineErrorInfo(
            2072,
            "BUSINESS_ERROR_02072", 
            "�����͏�񂪖����͂ł��B");

    /**
     * �K�p���ԁi���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02073 = errorMgr.defineErrorInfo(
            2073,
            "BUSINESS_ERROR_02073", 
            "�K�p���ԁi���j�������͂ł��B");

    /**
     * ������z�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02074 = errorMgr.defineErrorInfo(
            2074,
            "BUSINESS_ERROR_02074", 
            "������z�i���j�������͂ł��B");

    /**
     * ������z�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02075 = errorMgr.defineErrorInfo(
            2075,
            "BUSINESS_ERROR_02075", 
            "������z�i���j���}�C�i�X�̒l�ł��B");

    /**
     * ������z�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02076 = errorMgr.defineErrorInfo(
            2076,
            "BUSINESS_ERROR_02076", 
            "������z�i���j�̒l���s���ł��B");

    /**
     * ������z�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02077 = errorMgr.defineErrorInfo(
            2077,
            "BUSINESS_ERROR_02077", 
            "������z�i���j���}�C�i�X�̒l�ł��B");

    /**
     * ������z�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02078 = errorMgr.defineErrorInfo(
            2078,
            "BUSINESS_ERROR_02078", 
            "������z�i���j�̒l���s���ł��B");

    /**
     * ������z�i���j�Ǝ�����z�i���j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02079 = errorMgr.defineErrorInfo(
            2079,
            "BUSINESS_ERROR_02079", 
            "������z�i���j��������z�i���j�𒴂��܂����B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02080 = errorMgr.defineErrorInfo(
            2080,
            "BUSINESS_ERROR_02080", 
            "�������������͂ł��B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02081 = errorMgr.defineErrorInfo(
            2081,
            "BUSINESS_ERROR_02081", 
            "���������}�C�i�X�̒l�ł��B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02082 = errorMgr.defineErrorInfo(
            2082,
            "BUSINESS_ERROR_02082", 
            "�������̒l���s���ł��B");

    /**
     * �t�����z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02083 = errorMgr.defineErrorInfo(
            2083,
            "BUSINESS_ERROR_02083", 
            "�t�����z�������͂ł��B");

    /**
     * �t�����z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02084 = errorMgr.defineErrorInfo(
            2084,
            "BUSINESS_ERROR_02084", 
            "�t�����z���}�C�i�X�̒l�ł��B");

    /**
     * �t�����z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02085 = errorMgr.defineErrorInfo(
            2085,
            "BUSINESS_ERROR_02085", 
            "�t�����z�̒l���s���ł��B");

    /**
     * �Y�������Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02086 = errorMgr.defineErrorInfo(
            2086,
            "BUSINESS_ERROR_02086", 
            "�Y�����������݂��܂���B");

    /**
     * ����K�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02087 = errorMgr.defineErrorInfo(
            2087,
            "BUSINESS_ERROR_02087", 
            "�O�������������������K�����ł��B");

    /**
     * �O��������������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02088 = errorMgr.defineErrorInfo(
            2088,
            "BUSINESS_ERROR_02088", 
            "�O����������������擾�ł��܂���B");

    /**
     * ����������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02089 = errorMgr.defineErrorInfo(
            2089,
            "BUSINESS_ERROR_02089", 
            "�Y���������������ł��B");

    /**
     * ��������̏����ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02090 = errorMgr.defineErrorInfo(
            2090,
            "BUSINESS_ERROR_02090", 
            "�Y���������������\�Ȗ����ł͂���܂���B");

    /**
     * ���s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02091 = errorMgr.defineErrorInfo(
            2091,
            "BUSINESS_ERROR_02091", 
            "���s�����ŒP�����w��s�ł�");

    /**
     * �w�l�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02092 = errorMgr.defineErrorInfo(
            2092,
            "BUSINESS_ERROR_02092", 
            "�w�l�����ŒP�������w��ł��B");

    /**
     * �����P���T�C�Y�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02093 = errorMgr.defineErrorInfo(
            2093,
            "BUSINESS_ERROR_02093", 
            "�����P���T�C�Y���s���ł��B");

    /**
     * ���������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02094 = errorMgr.defineErrorInfo(
            2094,
            "BUSINESS_ERROR_02094", 
            "�����������Œᒍ�����������ł��B");

    /**
     * �O�݌��ϕs�\�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02095 = errorMgr.defineErrorInfo(
            2095,
            "BUSINESS_ERROR_02095", 
            "�O�݌��ϕs�\�G���[�B");

    /**
     * ����������J�݃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02096 = errorMgr.defineErrorInfo(
            2096,
            "BUSINESS_ERROR_02096", 
            "����������J�݃G���[�B");

    /**
     * �בփ��[�g���o�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02097 = errorMgr.defineErrorInfo(
            2097,
            "BUSINESS_ERROR_02097", 
            "�בփ��[�g���o�^�G���[�B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02098 = errorMgr.defineErrorInfo(
            2098,
            "BUSINESS_ERROR_02098", 
            "�������������ƈ�v���܂���B");

    /**
     * ���n��n���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02099 = errorMgr.defineErrorInfo(
            2099,
            "BUSINESS_ERROR_02099", 
            "�e���̌��n��n���Ǝw�肵�����n��n�����r���āA�s��v�ȕ������݂��܂��B");

    /**
     * ���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02100 = errorMgr.defineErrorInfo(
            2100,
            "BUSINESS_ERROR_02100", 
            "�������ŁA���P�����w�l�𒴂��Ă��܂��B");

    /**
     * ���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02101 = errorMgr.defineErrorInfo(
            2101,
            "BUSINESS_ERROR_02101", 
            "�������ŁA���P�����w�l��菬�����ł��B");

    /**
     * ���������敪����v���Ȃ����߁A�����s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02102 = errorMgr.defineErrorInfo(
            2102,
            "BUSINESS_ERROR_02102", 
            "���������敪����v���Ȃ����߁A�����s�ł��B");

    /**
     * �������͂Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02103 = errorMgr.defineErrorInfo(
            2103,
            "BUSINESS_ERROR_02103", 
            "�������͂���Ă��܂���B");

    /**
     * ��萔�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02104 = errorMgr.defineErrorInfo(
            2104,
            "BUSINESS_ERROR_02104", 
            "��萔�ʂ��������ʂ𒴂��Ă��܂��B");

    /**
     * �������ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02105 = errorMgr.defineErrorInfo(
            2105,
            "BUSINESS_ERROR_02105", 
            "�������ʂ��������ʂ�菬�����ł��B");

    /**
     * ���������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02106 = errorMgr.defineErrorInfo(
            2106,
            "BUSINESS_ERROR_02106", 
            "���������������s�ł��B");

    /**
     * �����ς݂̋t�w�l�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02107 = errorMgr.defineErrorInfo(
            2107,
            "BUSINESS_ERROR_02107", 
            "�����ς݂̋t�w�l�����Ȃ̂ŁA���������A�����������Z�q�A�������������P���������s�ł��B");

    /**
     * �s��戵�s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02108 = errorMgr.defineErrorInfo(
            2108,
            "BUSINESS_ERROR_02108", 
            "�s��戵�s�G���[�B");

    /**
     * ���t�\���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02109 = errorMgr.defineErrorInfo(
            2109,
            "BUSINESS_ERROR_02109", 
            "���͂��ꂽ���ʂ����t�\���ʂ𒴂��Ă��܂��B");

    /**
     * �ύX��T�Z�뉿�P�����s���Ȓl�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02110 = errorMgr.defineErrorInfo(
            2110,
            "BUSINESS_ERROR_02110", 
            "�ύX��T�Z�뉿�P�����s���Ȓl�ɂȂ��Ă��܂��B");

    /**
     * ���ϋ敪��null�ł���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02111 = errorMgr.defineErrorInfo(
            2111,
            "BUSINESS_ERROR_02111", 
            "���ϋ敪��null�ł��B");

    /**
     * ���ϋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02112 = errorMgr.defineErrorInfo(
            2112,
            "BUSINESS_ERROR_02112", 
            "���ϋ敪������`�̒l�ł��B");

    /**
     * ��������敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02113 = errorMgr.defineErrorInfo(
            2113,
            "BUSINESS_ERROR_02113", 
            "��������敪��null�ł��B");

    /**
     * ��������敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02114 = errorMgr.defineErrorInfo(
            2114,
            "BUSINESS_ERROR_02114", 
            "��������敪������`�̒l�ł��B");

    /**
     * ���s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02115 = errorMgr.defineErrorInfo(
            2115,
            "BUSINESS_ERROR_02115", 
            "���s����������`�̒l�ł��B");

    /**
     * ����������null�ł���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02116 = errorMgr.defineErrorInfo(
            2116,
            "BUSINESS_ERROR_02116", 
            "����������null�ł��B");

    /**
     * ���������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02117 = errorMgr.defineErrorInfo(
            2117,
            "BUSINESS_ERROR_02117", 
            "��������������`�̒l�ł��B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02118 = errorMgr.defineErrorInfo(
            2118,
            "BUSINESS_ERROR_02118", 
            "�����P�������w��ł��B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02119 = errorMgr.defineErrorInfo(
            2119,
            "BUSINESS_ERROR_02119", 
            "�����P�������l�ȊO�̒l�ł��B");

    /**
     * �����P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02120 = errorMgr.defineErrorInfo(
            2120,
            "BUSINESS_ERROR_02120", 
            "�����P����0�ȉ��̒l�ł��B");

    /**
     * ���������敪���g�t�w�l�h�̏ꍇ�́A�t�w�l�p���������P�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02121 = errorMgr.defineErrorInfo(
            2121,
            "BUSINESS_ERROR_02121", 
            "���������敪���g�t�w�l�h�Ȃ̂ɁA�t�w�l�p���������P�������w��ł��B");

    /**
     * ���������敪���g�t�w�l�h�̏ꍇ�́A�t�w�l�p���������P����0�ȉ��̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02122 = errorMgr.defineErrorInfo(
            2122,
            "BUSINESS_ERROR_02122", 
            "���������敪���g�t�w�l�h�ŁA�t�w�l�p���������P����0�ȉ��̒l�ł��B");

    /**
     * ���������敪���g�t�w�l�h�̏ꍇ�́A�t�w�l�p���������P�������l�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02123 = errorMgr.defineErrorInfo(
            2123,
            "BUSINESS_ERROR_02123", 
            "���������敪���g�t�w�l�h�ŁA�t�w�l�p���������P�������l�ȊO�̒l�ł��B");

    /**
     * ���������敪���g�t�w�l�h�̏ꍇ�́A�t�w�l�p���������P���̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02124 = errorMgr.defineErrorInfo(
            2124,
            "BUSINESS_ERROR_02124", 
            "���������敪���g�t�w�l�h�ŁA�t�w�l�p���������P���̃T�C�Y���s���ł��B");

    /**
     * ���������敪���g�t�w�l�h�̏ꍇ�́A�t�w�l�p�����������Z�q�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02125 = errorMgr.defineErrorInfo(
            2125,
            "BUSINESS_ERROR_02125", 
            "���������敪���g�t�w�l�h�ŁA�t�w�l�p�����������Z�q�����w��ł��B");

    /**
     * ���������敪���g�t�w�l�h�̏ꍇ�́A�t�w�l�p�����������Z�q������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02126 = errorMgr.defineErrorInfo(
            2126,
            "BUSINESS_ERROR_02126", 
            "���������敪���g�t�w�l�h�ŁA�t�w�l�p�����������Z�q������`�̒l�ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p�����������Z�q�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02127 = errorMgr.defineErrorInfo(
            2127,
            "BUSINESS_ERROR_02127", 
            "���������敪���gW�w�l�h�Ȃ̂ɁAW�w�l�p�����������Z�q�����w��ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p�����������Z�q������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02128 = errorMgr.defineErrorInfo(
            2128,
            "BUSINESS_ERROR_02128", 
            "���������敪���gW�w�l�h�ŁAW�w�l�p�����������Z�q������`�̒l�ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p�����P���敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02129 = errorMgr.defineErrorInfo(
            2129,
            "BUSINESS_ERROR_02129", 
            "���������敪���gW�w�l�h�Ȃ̂ɁAW�w�l�p�����P���敪�����w��ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p�����P���敪����`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02130 = errorMgr.defineErrorInfo(
            2130,
            "BUSINESS_ERROR_02130", 
            "���������敪���gW�w�l�h�ŁAW�w�l�p�����P���敪������`�̒l�ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p���������P�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02131 = errorMgr.defineErrorInfo(
            2131,
            "BUSINESS_ERROR_02131", 
            "���������敪���gW�w�l�h�Ȃ̂ɁAW�w�l�p���������P�������w��ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p���������P�������l�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02132 = errorMgr.defineErrorInfo(
            2132,
            "BUSINESS_ERROR_02132", 
            "���������敪���gW�w�l�h�ŁAW�w�l�p���������P�������l�ȊO�̒l�ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p���������P����0�ȉ��̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02133 = errorMgr.defineErrorInfo(
            2133,
            "BUSINESS_ERROR_02133", 
            "���������敪���gW�w�l�h�ŁAW�w�l�p���������P����0�ȉ��̒l�ł��B");

    /**
     * ���������敪���gW�w�l�h�̏ꍇ�́AW�w�l�p���������P���̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02134 = errorMgr.defineErrorInfo(
            2134,
            "BUSINESS_ERROR_02134", 
            "���������敪���gW�w�l�h�ŁAW�w�l�p���������P���̃T�C�Y���s���ł��B");

    /**
     * W�w�l�p�����P���敪���h�w�l�h �̏ꍇ�́AW�w�l�p�����P�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02135 = errorMgr.defineErrorInfo(
            2135,
            "BUSINESS_ERROR_02135", 
            "W�w�l�p�����P���敪���h�w�l�h�Ȃ̂ɁAW�w�l�p�����P�������w��ł��B");

    /**
     * W�w�l�p�����P���敪���h�w�l�h �̏ꍇ�́AW�w�l�p�����P�������l�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02136 = errorMgr.defineErrorInfo(
            2136,
            "BUSINESS_ERROR_02136", 
            "W�w�l�p�����P���敪���h�w�l�h�ŁAW�w�l�p�����P�������l�ȊO�̒l�ł��B");

    /**
     * W�w�l�p�����P���敪���h�w�l�h �̏ꍇ�́AW�w�l�p�����P����0�ȉ��̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02137 = errorMgr.defineErrorInfo(
            2137,
            "BUSINESS_ERROR_02137", 
            "W�w�l�p�����P���敪���h�w�l�h�ŁAW�w�l�p�����P����0�ȉ��̒l�ł��B");

    /**
     * W�w�l�p�����P���敪���h�w�l�h �̏ꍇ�́AW�w�l�p�����P���̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02138 = errorMgr.defineErrorInfo(
            2138,
            "BUSINESS_ERROR_02138", 
            "W�w�l�p�����P���敪���h�w�l�h �ŁAW�w�l�p�����P���̃T�C�Y���s���ł��B");

    /**
     * W�w�l�p�����P���敪���h���s�h �̏ꍇ�́AW�w�l�p�����P�����w��s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02139 = errorMgr.defineErrorInfo(
            2139,
            "BUSINESS_ERROR_02139", 
            "W�w�l�p�����P���敪���h���s�h �Ȃ̂ŁAW�w�l�p�����P�����w��s�ł��B");

    /**
     * �،���ЃR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02141 = errorMgr.defineErrorInfo(
            2141,
            "BUSINESS_ERROR_02141", 
            "�،���ЃR�[�h���}�C�i�X�̒l�ł��B");

    /**
     * �O�����������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02142 = errorMgr.defineErrorInfo(
            2142,
            "BUSINESS_ERROR_02142", 
            "�O�������������擾�ł��܂���B");

    /**
     * �������̏ꍇ�́A���s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02143 = errorMgr.defineErrorInfo(
            2143,
            "BUSINESS_ERROR_02143", 
            "�������Ȃ��̂Ȃ̂ŁA���s�ł��B");

    /**
     * �o���I�������ς݂̏ꍇ�́A���s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02144 = errorMgr.defineErrorInfo(
            2144,
            "BUSINESS_ERROR_02144", 
            "�o���I�������ς݂Ȃ̂ŁA���s�ł��B");

    /**
     * �ꕔ�o���̏ꍇ�́A���s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02145 = errorMgr.defineErrorInfo(
            2145,
            "BUSINESS_ERROR_02145", 
            "�ꕔ�o���Ȃ̂ŁA���s�ł��B");

    /**
     * �����͏�񂪓���łȂ��ꍇ�����f�[�^�̏ꍇ�́A�ύX�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02146 = errorMgr.defineErrorInfo(
            2146,
            "BUSINESS_ERROR_02146", 
            "�����͏�񂪓���ł͂Ȃ������f�[�^�Ȃ̂ŁA�ύX�s�ł��B");

    /**
     * �����͏��̈��҃R�[�h�ƌڋq.���҃R�[�h�iSONAR�j���s��v�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02147 = errorMgr.defineErrorInfo(
            2147,
            "BUSINESS_ERROR_02147", 
            "�����͏��̈��҃R�[�h�ƌڋq.���҃R�[�h�iSONAR�j���s��v�ł��B");

    /**
     * �����͏��.�����R�[�h�ƌ��n�����R�[�h�̗����������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02148 = errorMgr.defineErrorInfo(
            2148,
            "BUSINESS_ERROR_02148", 
            "�����͏��.�����R�[�h�ƌ��n�����R�[�h�̗����������͂ł��B");

    /**
     * �������̓��t���c�Ɠ��łȂ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02149 = errorMgr.defineErrorInfo(
            2149,
            "BUSINESS_ERROR_02149", 
            "�������̓��t���c�Ɠ��ł͂���܂���B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02150 = errorMgr.defineErrorInfo(
            2150,
            "BUSINESS_ERROR_02150", 
            "���������R�c�Ɠ��O���O�̓��t�ł��B");

    /**
     * �^�p�R�[�h,�`�[�ԍ�,��������,�����P�����V�X�e�������Z�b�g���ځA���͕s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02151 = errorMgr.defineErrorInfo(
            2151,
            "BUSINESS_ERROR_02151", 
            "�^�p�R�[�h,�`�[�ԍ�,��������,�����P�����V�X�e�������Z�b�g���ڂȂ̂ŁA���͕s�ł��B");

    /**
     * ���n�萔���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02152 = errorMgr.defineErrorInfo(
            2152,
            "BUSINESS_ERROR_02152", 
            "���n�萔�����L�������͈͊O�ł��B");

    /**
     * ���n����Ń`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02153 = errorMgr.defineErrorInfo(
            2153,
            "BUSINESS_ERROR_02153", 
            "���n����ł��L�������͈͊O�ł��B");

    /**
     * ���̑��R�X�g�P�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02154 = errorMgr.defineErrorInfo(
            2154,
            "BUSINESS_ERROR_02154", 
            "���̑��R�X�g�P���L�������͈͊O�ł��B");

    /**
     * ���̑��R�X�g�Q�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02155 = errorMgr.defineErrorInfo(
            2155,
            "BUSINESS_ERROR_02155", 
            "���̑��R�X�g�Q���L�������͈͊O�ł��B");

    /**
     * �J�����_�[���ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02156 = errorMgr.defineErrorInfo(
            2156,
            "BUSINESS_ERROR_02156", 
            "�J�����_�[���ꗗ�����݂��܂���B");

    /**
     * ���t�����͓��t�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02157 = errorMgr.defineErrorInfo(
            2157,
            "BUSINESS_ERROR_02157", 
            "���t�����͓��t�敪��null�ł��B");

    /**
     * ���t�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02158 = errorMgr.defineErrorInfo(
            2158,
            "BUSINESS_ERROR_02158", 
            "���t�敪������`�̒l�ł��B");

    /**
     * �ב֏��̃��[�g�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02159 = errorMgr.defineErrorInfo(
            2159,
            "BUSINESS_ERROR_02159", 
            "�ב֏��̃��[�g�敪������`�̒l�ł��B");

    /**
     * �������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02160 = errorMgr.defineErrorInfo(
            2160,
            "BUSINESS_ERROR_02160", 
            "�������G���[�B");

    /**
     * �������̏ꍇ�́A�o���s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02161 = errorMgr.defineErrorInfo(
            2161,
            "BUSINESS_ERROR_02161", 
            "�������Ȃ̂ŁA�o���s�ł��B");

    /**
     * �o���I�������ς݂̏ꍇ�́A�o���s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02162 = errorMgr.defineErrorInfo(
            2162,
            "BUSINESS_ERROR_02162", 
            "�o���I�������ς݂Ȃ̂ŁA�o���s�ł��B");

    /**
     * HOST�����̏ꍇ�́A�o���s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02163 = errorMgr.defineErrorInfo(
            2163,
            "BUSINESS_ERROR_02163", 
            "HOST�����Ȃ̂ŁA�o���s�ł��B");

    /**
     * �o���I�������ς݂̏ꍇ�́A�o��������s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02164 = errorMgr.defineErrorInfo(
            2164,
            "BUSINESS_ERROR_02164", 
            "�o���I�������ς݂Ȃ̂ŁA�o��������s�ł��B");

    /**
     * �O�����������P�ʃI�u�W�F�N�g���擾�ł��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02165 = errorMgr.defineErrorInfo(
            2165,
            "BUSINESS_ERROR_02165", 
            "�O�����������P�ʃI�u�W�F�N�g���擾�ł��܂���B");

    /**
     * �o���I����̒����̏ꍇ�́A������t���ʃA�b�v���[�h�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02166 = errorMgr.defineErrorInfo(
            2166,
            "BUSINESS_ERROR_02166", 
            "�o���I����̒����́A������t���ʃA�b�v���[�h�s�ł��B");

    /**
     * ������ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02167 = errorMgr.defineErrorInfo(
            2167,
            "BUSINESS_ERROR_02167", 
            "������ʂ���v���Ȃ����߁A������t���ʃA�b�v���[�h�s�ł��B");

    /**
     * �����@@�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02168 = errorMgr.defineErrorInfo(
            2168,
            "BUSINESS_ERROR_02168", 
            "�����@@���O�������������ʈꊇ����CSV.�����@@�ƈ�v���܂���B");

    /**
     * ���͍s�ɃG���[������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02169 = errorMgr.defineErrorInfo(
            2169,
            "BUSINESS_ERROR_02169", 
            "���͍s�ɃG���[������܂��B");

    /**
     * ���m���D�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02170 = errorMgr.defineErrorInfo(
            2170,
            "BUSINESS_ERROR_02170", 
            "���m���D���L�������͈͊O�ł��B");

    /**
     * �ʌo�H�œo�^���ꂽ���œ�����ʔԂ����ɑ��݂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02171 = errorMgr.defineErrorInfo(
            2171,
            "BUSINESS_ERROR_02171", 
            "�ʌo�H�œo�^���ꂽ���œ�����ʔԂ����ɑ��݂��܂��B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02172 = errorMgr.defineErrorInfo(
            2172,
            "BUSINESS_ERROR_02172", 
            "���������擾�ł��܂���B");

    /**
     * ���No.�d���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02173 = errorMgr.defineErrorInfo(
            2173,
            "BUSINESS_ERROR_02173", 
            "�w��s�ԍ��̉^�p�R�[�h�����No.�Ɠ����l�̍s�����݂��܂��B");

    /**
     * ���X�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02174 = errorMgr.defineErrorInfo(
            2174,
            "BUSINESS_ERROR_02174", 
            "���X�R�[�h��null�ł��B");

    /**
     * ���X�R�[�h�̗v�f���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02175 = errorMgr.defineErrorInfo(
            2175,
            "BUSINESS_ERROR_02175", 
            "���X�R�[�h�̗v�f����0�ł��B");

    /**
     * �w�肳�ꂽ���������͏o�����͈ꗗ�ł͎g�p�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02176 = errorMgr.defineErrorInfo(
            2176,
            "BUSINESS_ERROR_02176", 
            "�w�肳�ꂽ���������͏o�����͈ꗗ�ł͎g�p�ł��܂���B");

    /**
     * ��������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02177 = errorMgr.defineErrorInfo(
            2177,
            "BUSINESS_ERROR_02177", 
            "��������s�B");

    /**
     * �����ΏۊO�f�[�^�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02178 = errorMgr.defineErrorInfo(
            2178,
            "BUSINESS_ERROR_02178", 
            "�����ΏۊO�f�[�^�B");

    /**
     * ���i�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02182 = errorMgr.defineErrorInfo(
            2182,
            "BUSINESS_ERROR_02182", 
            "���i�敪�����w��ł��B");

    /**
     * �o�^�f�[�^�s�����i�d���o�^�s�j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02183 = errorMgr.defineErrorInfo(
            2183,
            "BUSINESS_ERROR_02183", 
            "�o�^�f�[�^�s�����i�d���o�^�s�j�B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02184 = errorMgr.defineErrorInfo(
            2184,
            "BUSINESS_ERROR_02184", 
            "�������������͂ł��B");

    /**
     * ����Rev.�̒l���ő包���𒴉߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02185 = errorMgr.defineErrorInfo(
            2185,
            "BUSINESS_ERROR_02185", 
            "����Rev.�̒l���ő包���𒴉߁B");

    /**
     * ��萔�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02186 = errorMgr.defineErrorInfo(
            2186,
            "BUSINESS_ERROR_02186", 
            "��萔�ʂ�0�ȉ��̒l�ł��B");

    /**
     * ������ؑփe�[�u���ݒ�s���i�L���f�[�^���������݁j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02187 = errorMgr.defineErrorInfo(
            2187,
            "BUSINESS_ERROR_02187", 
            "������ؑփe�[�u���ݒ�s���i�L���f�[�^���������݁j�B");

    /**
     * �t�k�Ώۍs�����݂��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02188 = errorMgr.defineErrorInfo(
            2188,
            "BUSINESS_ERROR_02188", 
            "�t�k�Ώۍs�����݂��܂���B");

    /**
     * �����萔���i�~�݁j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02189 = errorMgr.defineErrorInfo(
            2189,
            "BUSINESS_ERROR_02189", 
            "�����萔���i�~�݁j���L�������͈͊O�ł��B");

    /**
     * ����Łi�~�݁j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02190 = errorMgr.defineErrorInfo(
            2190,
            "BUSINESS_ERROR_02190", 
            "����Łi�~�݁j���L�������͈͊O�ł��B");

    /**
     * ���No.�����l�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02191 = errorMgr.defineErrorInfo(
            2191,
            "BUSINESS_ERROR_02191", 
            "���No.�����l�ȊO�̒l�ł��B");

    /**
     * �������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02192 = errorMgr.defineErrorInfo(
            2192,
            "BUSINESS_ERROR_02192", 
            "�������̃t�H�[�}�b�g���hyyyyMMddHHmmss�h�ł͂���܂���B");

    /**
     * ���בփ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02193 = errorMgr.defineErrorInfo(
            2193,
            "BUSINESS_ERROR_02193", 
            "���בւ����w��ł��B");

    /**
     * ���בփ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02194 = errorMgr.defineErrorInfo(
            2194,
            "BUSINESS_ERROR_02194", 
            "���בւ̗L���������A�������R���C�������S���͈̔͊O�ł��B");

    /**
     * �ב֏��ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02195 = errorMgr.defineErrorInfo(
            2195,
            "BUSINESS_ERROR_02195", 
            "�ב֏��ꗗ�������͂ł��B");

    /**
     * ���בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02196 = errorMgr.defineErrorInfo(
            2196,
            "BUSINESS_ERROR_02196", 
            "���בփ��[�g��0�ȉ��̒l�ł��B");

    /**
     * �������ړ��������s�i�x�e���ԑсj�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02197 = errorMgr.defineErrorInfo(
            2197,
            "BUSINESS_ERROR_02197", 
            "�������ړ��������s�i�x�e���ԑсj�B");

    /**
     * ���N�����N�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02198 = errorMgr.defineErrorInfo(
            2198,
            "BUSINESS_ERROR_02198", 
            "���N�����N�������w��ł��B");

    /**
     * ���N���������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02199 = errorMgr.defineErrorInfo(
            2199,
            "BUSINESS_ERROR_02199", 
            "���N���������w��ł��B");

    /**
     * �ڋq�̔N��v���t�@@�����X�̒l��菬�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02200 = errorMgr.defineErrorInfo(
            2200,
            "BUSINESS_ERROR_02200", 
            "�ڋq�̔N��v���t�@@�����X�̒l��菬�����ł��B");

    /**
     * �ڋq�̔N��v���t�@@�����X�̒l��菬�����Ȃ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02201 = errorMgr.defineErrorInfo(
            2201,
            "BUSINESS_ERROR_02201", 
            "�ڋq�̔N��v���t�@@�����X�̒l��菬�����Ȃ��ł��B");

    /**
     * ��ʃR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02202 = errorMgr.defineErrorInfo(
            2202,
            "BUSINESS_ERROR_02202", 
            "��ʃR�[�h�����w��ł��B");

    /**
     * �Y����������������s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02203 = errorMgr.defineErrorInfo(
            2203,
            "BUSINESS_ERROR_02203", 
            "�Y����������������s�ł��B");

    /**
     * ���������s�̎s��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02204 = errorMgr.defineErrorInfo(
            2204,
            "BUSINESS_ERROR_02204", 
            "���������s�̎s��ł��B");

    /**
     * �ؑ֏��������敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02205 = errorMgr.defineErrorInfo(
            2205,
            "BUSINESS_ERROR_02205", 
            "�ؑ֏��������敪�����w��ł��B");

    /**
     * �ؑ֋N���敪�i�s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02206 = errorMgr.defineErrorInfo(
            2206,
            "BUSINESS_ERROR_02206", 
            "�ؑ֏����i�s���ׁ̈A�ؑ֕s�B");

    /**
     * �T�[�r�X�N���敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02207 = errorMgr.defineErrorInfo(
            2207,
            "BUSINESS_ERROR_02207", 
            "�T�[�r�X�N���敪�����w��ł��B");

    /**
     * �S���������N���σ��b�Z�[�W�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02208 = errorMgr.defineErrorInfo(
            2208,
            "BUSINESS_ERROR_02208", 
            "���ɐؑ֏����N���ςł��B");

    /**
     * �ϊ��s��R�[�h���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02209 = errorMgr.defineErrorInfo(
            2209,
            "BUSINESS_ERROR_02209", 
            "�ϊ��s��R�[�h�����w��ł��B");

    /**
     * �����o�H�敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02210 = errorMgr.defineErrorInfo(
            2210,
            "BUSINESS_ERROR_02210", 
            "�����o�H�敪�����w��ł��B");

    /**
     * �ؑ֋N���敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02211 = errorMgr.defineErrorInfo(
            2211,
            "BUSINESS_ERROR_02211", 
            "�ؑ֋N���敪�����w��ł��B");

    /**
     * �t�����g�����V�X�e���敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02212 = errorMgr.defineErrorInfo(
            2212,
            "BUSINESS_ERROR_02212", 
            "�t�����g�����V�X�e���敪�����w��ł��B");

    /**
     * SONAR�S��Q�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02213 = errorMgr.defineErrorInfo(
            2213,
            "BUSINESS_ERROR_02213", 
            "SONAR�S��Q�̈�,�ؑ֏��������s�ł��܂���B");

    /**
     * �L���t���O���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02214 = errorMgr.defineErrorInfo(
            2214,
            "BUSINESS_ERROR_02214", 
            "�L���t���O�����w��ł��B");

    /**
     * �ύX��L���t���O���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02215 = errorMgr.defineErrorInfo(
            2215,
            "BUSINESS_ERROR_02215", 
            "�ύX��L���t���O�����w��ł��B");

    /**
     * �����o�H�֑ؑΏۂȂ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02216 = errorMgr.defineErrorInfo(
            2216,
            "BUSINESS_ERROR_02216", 
            "�����o�H�֑ؑΏۂ����݂��܂���B");

    /**
     * �ʒm��M���t���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02217 = errorMgr.defineErrorInfo(
            2217,
            "BUSINESS_ERROR_02217", 
            "�ʒm��M���t�����w��ł��B");

    /**
     * �ʒm��M����From�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02218 = errorMgr.defineErrorInfo(
            2218,
            "BUSINESS_ERROR_02218", 
            "�ʒm��M����From���s���ł��B");

    /**
     * �ʒm��M����To�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02219 = errorMgr.defineErrorInfo(
            2219,
            "BUSINESS_ERROR_02219", 
            "�ʒm��M����To���s���ł��B");

    /**
     * ���בփ��[�g�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02220 = errorMgr.defineErrorInfo(
            2220,
            "BUSINESS_ERROR_02220", 
            "���בփ��[�g�����l�ȊO�̒l�ł��B");

    /**
     * �o�^���i���j�Ɠo�^���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02221 = errorMgr.defineErrorInfo(
            2221,
            "BUSINESS_ERROR_02221", 
            "�o�^���i���j�Ɠo�^���i���j�����𖢓��͂��A�����ɓ��͂��Ȃ���΂Ȃ�Ȃ��B");

    /**
     * �o�^���i���j���o�^���i���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02222 = errorMgr.defineErrorInfo(
            2222,
            "BUSINESS_ERROR_02222", 
            "�o�^���i���j���o�^���i���j�𒴂��Ă��܂��B");

    /**
     * ���������敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02223 = errorMgr.defineErrorInfo(
            2223,
            "BUSINESS_ERROR_02223", 
            "���������敪���s���ȃR�[�h�l�ł��B");

    /**
     * �y�[�W���\���s���������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02224 = errorMgr.defineErrorInfo(
            2224,
            "BUSINESS_ERROR_02224", 
            "�y�[�W���\���s���������͂ł��B");

    /**
     * ��~�󋵓o�^���R�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02225 = errorMgr.defineErrorInfo(
            2225,
            "BUSINESS_ERROR_02225", 
            "��~�󋵓o�^���R�̕�������40byte���傫�������ł��B");

    /**
     * ��~�󋵓o�^���R�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02226 = errorMgr.defineErrorInfo(
            2226,
            "BUSINESS_ERROR_02226", 
            "��~�󋵓o�^���R���S�p�����ȊO���܂܂�Ă���ł��B");

    /**
     * ��W�Œᐔ�ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02227 = errorMgr.defineErrorInfo(
            2227,
            "BUSINESS_ERROR_02227", 
            "��W�Œᐔ�ʃG���[(�������� �� ��W�Œᐔ��)�B");

    /**
     * ��W�P�ʐ��ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02228 = errorMgr.defineErrorInfo(
            2228,
            "BUSINESS_ERROR_02228", 
            "��W�P�ʐ��ʃG���[(�������ʂ���W�P�ʐ��ʂŊ���؂�Ȃ�)�B");

    /**
     * ����ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02229 = errorMgr.defineErrorInfo(
            2229,
            "BUSINESS_ERROR_02229", 
            "����ID�����w��ł��B");

    /**
     * ��W���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02230 = errorMgr.defineErrorInfo(
            2230,
            "BUSINESS_ERROR_02230", 
            "��W���ʂ����w��ł��B");

    /**
     * ��W���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02231 = errorMgr.defineErrorInfo(
            2231,
            "BUSINESS_ERROR_02231", 
            "��W���ʂ����l�ȊO�̒l�ł��B");

    /**
     * ��W���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02232 = errorMgr.defineErrorInfo(
            2232,
            "BUSINESS_ERROR_02232", 
            "��W���ʂ�0�ȉ��̒l�ł���B");

    /**
     * ��W���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02233 = errorMgr.defineErrorInfo(
            2233,
            "BUSINESS_ERROR_02233", 
            "��W���ʂ�10���𒴂��܂����B");

    /**
     * �A�������͒l�i�����w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02234 = errorMgr.defineErrorInfo(
            2234,
            "BUSINESS_ERROR_02234", 
            "�A�������͒l�i�����w��s�B");

    /**
     * �A�������͎��s�����w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02235 = errorMgr.defineErrorInfo(
            2235,
            "BUSINESS_ERROR_02235", 
            "�A�������͎��s�����w��s�B");

    /**
     * �A�������͔��������w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02236 = errorMgr.defineErrorInfo(
            2236,
            "BUSINESS_ERROR_02236", 
            "�A�������͔��������w��s�B");

    /**
     * ��~�󋵓o�^���R�ύX�s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02237 = errorMgr.defineErrorInfo(
            2237,
            "BUSINESS_ERROR_02237", 
            "��~�󋵓o�^���R�ύX�s�G���[�B");

    /**
     * SONAR�����M�f�[�^�����邽�ߕύX�s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02238 = errorMgr.defineErrorInfo(
            2238,
            "BUSINESS_ERROR_02238", 
            "SONAR�����M�f�[�^�����邽�ߕύX�s�G���[�B");

    /**
     * �����敪���g5�F��W�h�Ɗg�����M�����������W�s�̏ꍇ�́A�戵�s�\�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02239 = errorMgr.defineErrorInfo(
            2239,
            "BUSINESS_ERROR_02239", 
            "�����敪���g5�F��W�h�Ɗg�����M�����������W�s�̏ꍇ�́A�戵�s�\�ł��B");

    /**
     * �s��ǌ�`�����J�z�I���܂ł̊Ԃ́A�A��������t��s�Ƃ���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02240 = errorMgr.defineErrorInfo(
            2240,
            "BUSINESS_ERROR_02240", 
            "�s��ǌ�`�����J�z�I���܂ł̊Ԃ́A�A��������t��s�Ƃ���B");

    /**
     * ���\�[�g�L�[�͏��i�敪�̂ݎw��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02241 = errorMgr.defineErrorInfo(
            2241,
            "BUSINESS_ERROR_02241", 
            "���\�[�g�L�[�͏��i�敪�̂ݎw��B");

    /**
     * �e�������S����肵�Ă��܂���B�i�A�����������j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02242 = errorMgr.defineErrorInfo(
            2242,
            "BUSINESS_ERROR_02242", 
            "�e�������S����肵�Ă��܂���B�i�A�����������j");

    /**
     * ���������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02243 = errorMgr.defineErrorInfo(
            2243,
            "BUSINESS_ERROR_02243", 
            "���������w��ł��B");

    /**
     * �����̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02244 = errorMgr.defineErrorInfo(
            2244,
            "BUSINESS_ERROR_02244", 
            "�����̒l���s���ł��B");

    /**
     * �w��̘A����������́A���Y�����ɐݒ�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02245 = errorMgr.defineErrorInfo(
            2245,
            "BUSINESS_ERROR_02245", 
            "�w��̘A����������́A���Y�����ɐݒ�s�ł��B");

    /**
     * �A�������ő�ݒ萔�𒴉߂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02247 = errorMgr.defineErrorInfo(
            2247,
            "BUSINESS_ERROR_02247", 
            "�A�������ő�ݒ萔�𒴉߂��Ă��܂��B");

    /**
     * �A�������戵�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02248 = errorMgr.defineErrorInfo(
            2248,
            "BUSINESS_ERROR_02248", 
            "�A�������戵�s�ł��B");

    /**
     * �e�������N���[�Y�ς̂��߃g���K�[�����ݒ�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02249 = errorMgr.defineErrorInfo(
            2249,
            "BUSINESS_ERROR_02249", 
            "�e�������N���[�Y�ς̂��߃g���K�[�����ݒ�s�ł��B");

    /**
     * ���Ύ�����̖����w�肪�A�e�����ƕs�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02250 = errorMgr.defineErrorInfo(
            2250,
            "BUSINESS_ERROR_02250", 
            "���Ύ�����̖����w�肪�A�e�����ƕs�����ł��B");

    /**
     * �A���������ʏ�񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02251 = errorMgr.defineErrorInfo(
            2251,
            "BUSINESS_ERROR_02251", 
            "�A���������ʏ�񂪖��w��ł��B");

    /**
     * �A����������敪�̒l�������ΏۊO�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02252 = errorMgr.defineErrorInfo(
            2252,
            "BUSINESS_ERROR_02252", 
            "�A����������敪�̒l�������ΏۊO�ł��B");

    /**
     * �A����������敪���A�A�������́}�w�l�w��s�̋敪�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02253 = errorMgr.defineErrorInfo(
            2253,
            "BUSINESS_ERROR_02253", 
            "�A����������敪���A�A�������́}�w�l�w��s�̋敪�ł��B");

    /**
     * �P�������l�ƒ����P���敪�̎w�肪�s�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02254 = errorMgr.defineErrorInfo(
            2254,
            "BUSINESS_ERROR_02254", 
            "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");

    /**
     * ����敪���A�������̏����ΏۊO�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02255 = errorMgr.defineErrorInfo(
            2255,
            "BUSINESS_ERROR_02255", 
            "����敪���A�������̏����ΏۊO�ł��B");

    /**
     * ���Ύ�����͖����R�[�h�w��͕K�{�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02256 = errorMgr.defineErrorInfo(
            2256,
            "BUSINESS_ERROR_02256", 
            "���Ύ�����͖����R�[�h�w��͕K�{�ł��B");

    /**
     * ���Ύ�����͎s��R�[�h�w��͕K�{�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02257 = errorMgr.defineErrorInfo(
            2257,
            "BUSINESS_ERROR_02257", 
            "���Ύ�����͎s��R�[�h�w��͕K�{�ł��B");

    /**
     * �i�e�����j����ID�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02258 = errorMgr.defineErrorInfo(
            2258,
            "BUSINESS_ERROR_02258", 
            "�i�e�����j����ID�����w��ł��B");

    /**
     * �i�e�����j����ID�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02259 = errorMgr.defineErrorInfo(
            2259,
            "BUSINESS_ERROR_02259", 
            "�i�e�����j����ID�̒l���s���ł��B");

    /**
     * �P�������l�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02260 = errorMgr.defineErrorInfo(
            2260,
            "BUSINESS_ERROR_02260", 
            "�P�������l�����w��ł��B");

    /**
     * �P�������l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02261 = errorMgr.defineErrorInfo(
            2261,
            "BUSINESS_ERROR_02261", 
            "�P�������l���s���ł��B");

    /**
     * �A����������敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02262 = errorMgr.defineErrorInfo(
            2262,
            "BUSINESS_ERROR_02262", 
            "�A����������敪�����w��ł��B");

    /**
     * �A����������敪�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02263 = errorMgr.defineErrorInfo(
            2263,
            "BUSINESS_ERROR_02263", 
            "�A����������敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �w����@@�i��W�j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02264 = errorMgr.defineErrorInfo(
            2264,
            "BUSINESS_ERROR_02264", 
            "�w����@@�i��W�j�̎w��Ɍ�肪����܂��B");

    /**
     * ��W�\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02265 = errorMgr.defineErrorInfo(
            2265,
            "BUSINESS_ERROR_02265", 
            "��W�\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * ��W�\�敪�i�����������j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02266 = errorMgr.defineErrorInfo(
            2266,
            "BUSINESS_ERROR_02266", 
            "��W�\�敪�i�����������j�̎w��Ɍ�肪����܂��B");

    /**
     * ���t�����敪�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02267 = errorMgr.defineErrorInfo(
            2267,
            "BUSINESS_ERROR_02267", 
            "���t�����敪�̎w��Ɍ�肪����܂��B");

    /**
     * �抷�����ID�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02268 = errorMgr.defineErrorInfo(
            2268,
            "BUSINESS_ERROR_02268", 
            "�抷�����ID�����w��ł��B");

    /**
     * �������S�����𒴉߂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02269 = errorMgr.defineErrorInfo(
            2269,
            "BUSINESS_ERROR_02269", 
            "�������S�����𒴉߂��Ă��܂�");

    /**
     * �抷�\�c�������s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02270 = errorMgr.defineErrorInfo(
            2270,
            "BUSINESS_ERROR_02270", 
            "�抷�\�c�������s���G���[�B");

    /**
     * ��n���@@�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02271 = errorMgr.defineErrorInfo(
            2271,
            "BUSINESS_ERROR_02271", 
            "��n���@@�`�F�b�N�G���[�B");

    /**
     * ��W�J�n���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02272 = errorMgr.defineErrorInfo(
            2272,
            "BUSINESS_ERROR_02272", 
            "��W�J�n���G���[�B");

    /**
     * ��W�I�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02273 = errorMgr.defineErrorInfo(
            2273,
            "BUSINESS_ERROR_02273", 
            "��W�I�����G���[�B");

    /**
     * �Œ�����i��W�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02274 = errorMgr.defineErrorInfo(
            2274,
            "BUSINESS_ERROR_02274", 
            "�Œ�����i��W�j�G���[�B");

    /**
     * �P�ʌ����i��W�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02275 = errorMgr.defineErrorInfo(
            2275,
            "BUSINESS_ERROR_02275", 
            "�P�ʌ����i��W�j�G���[�B");

    /**
     * �Œ���z�i��W�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02276 = errorMgr.defineErrorInfo(
            2276,
            "BUSINESS_ERROR_02276", 
            "�Œ���z�i��W�j�G���[�B");

    /**
     * �P�ʋ��z�i��W�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02277 = errorMgr.defineErrorInfo(
            2277,
            "BUSINESS_ERROR_02277", 
            "�P�ʋ��z�i��W�j�G���[�B");

    /**
     * �������z�w�蒍������v���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02278 = errorMgr.defineErrorInfo(
            2278,
            "BUSINESS_ERROR_02278", 
            "�������z�w�蒍������v���B");

    /**
     * ����Ĕ����v���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02279 = errorMgr.defineErrorInfo(
            2279,
            "BUSINESS_ERROR_02279", 
            "����Ĕ����v���B");

    /**
     * ������񒍕�����v���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02280 = errorMgr.defineErrorInfo(
            2280,
            "BUSINESS_ERROR_02280", 
            "������񒍕�����v���B");

    /**
     * �w����@@�i��W�j�G���[(�h�����h���w��s��)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02281 = errorMgr.defineErrorInfo(
            2281,
            "BUSINESS_ERROR_02281", 
            "�w����@@�i��W�j�G���[(�h�����h���w��s��)�B");

    /**
     * �Œ�����i��W�j���邢�͒P�ʌ����i��W�j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02282 = errorMgr.defineErrorInfo(
            2282,
            "BUSINESS_ERROR_02282", 
            "�Œ�����i��W�j���邢�͒P�ʌ����i��W�j�̎w��Ɍ�肪����܂��B");

    /**
     * �w����@@�i��W�j�G���[(�h���z�h���w��s��)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02283 = errorMgr.defineErrorInfo(
            2283,
            "BUSINESS_ERROR_02283", 
            "�w����@@�i��W�j�G���[(�h���z�h���w��s��)�B");

    /**
     * �Œ���z�i��W�j���邢�͒P�ʋ��z�i��W�j�̎w��Ɍ�肪����܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02284 = errorMgr.defineErrorInfo(
            2284,
            "BUSINESS_ERROR_02284", 
            "�Œ���z�i��W�j���邢�͒P�ʋ��z�i��W�j�̎w��Ɍ�肪����܂��B");

    /**
     * ���ϖ��ג��������w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02285 = errorMgr.defineErrorInfo(
            2285,
            "BUSINESS_ERROR_02285", 
            "���ϖ��ׂ̒��������w�肪�s���B");

    /**
     * ��n���@@���h��s�U���݁h�̏ꍇ�A���ϕ��@@�͕K���h�~�݁h��I�����Ă��������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02286 = errorMgr.defineErrorInfo(
            2286,
            "BUSINESS_ERROR_02286", 
            "��n���@@���h��s�U���݁h�̏ꍇ�A���ϕ��@@�͕K���h�~�݁h��I�����Ă��������B");

    /**
     * �w��̗\�񒍕��̓N���[�Y�ςł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02287 = errorMgr.defineErrorInfo(
            2287,
            "BUSINESS_ERROR_02287", 
            "�w��̗\�񒍕��̓N���[�Y�ςł��B");

    /**
     * �w��̗\�񒍕��͔������𒴉߂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02288 = errorMgr.defineErrorInfo(
            2288,
            "BUSINESS_ERROR_02288", 
            "�w��̗\�񒍕��͔������𒴉߂��Ă��܂��B");

    /**
     * �\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02289 = errorMgr.defineErrorInfo(
            2289,
            "BUSINESS_ERROR_02289", 
            "�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B");

    /**
     * �����������e�����̒������ʂ𒴉߂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02290 = errorMgr.defineErrorInfo(
            2290,
            "BUSINESS_ERROR_02290", 
            "�����������e�����̒������ʂ𒴉߂��Ă��܂��B");

    /**
     * �e�������������n�����̏ꍇ�A�}�w�l�͎w��s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02291 = errorMgr.defineErrorInfo(
            2291,
            "BUSINESS_ERROR_02291", 
            "�e�������������n�����̏ꍇ�A�}�w�l�͎w��s�ł��B");

    /**
     * �m�F���T�Z��n����̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02292 = errorMgr.defineErrorInfo(
            2292,
            "BUSINESS_ERROR_02292", 
            "�m�F���T�Z��n����̒l���s���ł��B");

    /**
     * ��������`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02295 = errorMgr.defineErrorInfo(
            2295,
            "BUSINESS_ERROR_02295", 
            "���n���t��������n���o���������Ă��܂��B");

    /**
     * ���[���A�h���X�폜�t���O�C�ē����[�����M�t���O�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02296 = errorMgr.defineErrorInfo(
            2296,
            "BUSINESS_ERROR_02296", 
            "�폜�ƈē����[�����M�i�v�j�����Ɏw��ł��Ȃ��B");

    /**
     * �w����@@�i��W�j�G���[(�h�I���w��h���w��s��)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02297 = errorMgr.defineErrorInfo(
            2297,
            "BUSINESS_ERROR_02297", 
            "�w����@@�i��W�j�G���[(�h�I���w��h���w��s��)�B");

    /**
     * ���s�P����0�ȉ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02298 = errorMgr.defineErrorInfo(
            2298,
            "BUSINESS_ERROR_02298", 
            "���s�P����0�ȉ��ł��B");

    /**
     * �i���S�ۖ����j�a����s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02299 = errorMgr.defineErrorInfo(
            2299,
            "BUSINESS_ERROR_02299", 
            "�i���S�ۖ����j�a����s���B");

    /**
     * �i���S�ۖ����j�ۏ؋��s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02300 = errorMgr.defineErrorInfo(
            2300,
            "BUSINESS_ERROR_02300", 
            "�i���S�ۖ����j�ۏ؋��s���B");

    /**
     * ��c�Ɠ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02301 = errorMgr.defineErrorInfo(
            2301,
            "BUSINESS_ERROR_02301", 
            "�Y���s�ꂪ�x�Ɠ��ׁ̈A�����ł��܂���B");

    /**
     * �_�E�����[�h�\���ԊO�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02302 = errorMgr.defineErrorInfo(
            2302,
            "BUSINESS_ERROR_02302", 
            "�_�E�����[�h�\���ԊO�ł��B");

    /**
     * ���Ϗ����`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02304 = errorMgr.defineErrorInfo(
            2304,
            "BUSINESS_ERROR_02304", 
            "�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B");

    /**
     * �A�������E���Ύ�����̌��Ϗ����敪�w��s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02306 = errorMgr.defineErrorInfo(
            2306,
            "BUSINESS_ERROR_02306", 
            "�A�������̔��Ύ���w�莞�́A���Ϗ����敪�Ɂi�����_���^�P���v���^�P�������j�̂����ꂩ���w�肵�Ă��������B");

    /**
     * ���I�m�菈���������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02307 = errorMgr.defineErrorInfo(
            2307,
            "BUSINESS_ERROR_02307", 
            "���I�m�菈���������Ȃ��Ă��܂���B");

    /**
     * ���I�Ώۃ��R�[�h�Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02308 = errorMgr.defineErrorInfo(
            2308,
            "BUSINESS_ERROR_02308", 
            "���I���R�[�h�����݂��܂���B");

    /**
     * ���I�m�菈���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02309 = errorMgr.defineErrorInfo(
            2309,
            "BUSINESS_ERROR_02309", 
            "���I�����͌��ʊm�蒆�A�������͌��ʊm�肪�I�����Ă��܂��B");

    /**
     * ���I�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02310 = errorMgr.defineErrorInfo(
            2310,
            "BUSINESS_ERROR_02310", 
            "���I�������ɃG���[���������܂����B");

    /**
     * ���I�������ԃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02311 = errorMgr.defineErrorInfo(
            2311,
            "BUSINESS_ERROR_02311", 
            "���I�E�������Ԃł͂���܂���B");

    /**
     * ���I�Ώیڋq�Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02312 = errorMgr.defineErrorInfo(
            2312,
            "BUSINESS_ERROR_02312", 
            "���I�Ώیڋq�����݂��܂���B");

    /**
     * �����\���ʒ��߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02313 = errorMgr.defineErrorInfo(
            2313,
            "BUSINESS_ERROR_02313", 
            "�������g���ʂ������\���ʂ𒴉߂��Ă��܂��B");

    /**
     * �������g���ʓ��͒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02314 = errorMgr.defineErrorInfo(
            2314,
            "BUSINESS_ERROR_02314", 
            "�������g���ʂ����l�ȊO�ł��B");

    /**
     * ����������ʓ��͒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02315 = errorMgr.defineErrorInfo(
            2315,
            "BUSINESS_ERROR_02315", 
            "����������ʂ����l�ȊO�ł��B");

    /**
     * �⌇�������g���ʓ��͒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02316 = errorMgr.defineErrorInfo(
            2316,
            "BUSINESS_ERROR_02316", 
            "�⌇�������g���ʂ����l�ȊO�ł��B");

    /**
     * �⌇����������ʓ��͒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02317 = errorMgr.defineErrorInfo(
            2317,
            "BUSINESS_ERROR_02317", 
            "�⌇����������ʂ����l�ȊO�ł��B");

    /**
     * Batch��������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02318 = errorMgr.defineErrorInfo(
            2318,
            "BUSINESS_ERROR_02318", 
            "���I�������s���́A�Ē��I�ł��܂���B");

    /**
     * �������g���ʐ����{�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02319 = errorMgr.defineErrorInfo(
            2319,
            "BUSINESS_ERROR_02319", 
            "�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");

    /**
     * ����������ʐ����{�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02320 = errorMgr.defineErrorInfo(
            2320,
            "BUSINESS_ERROR_02320", 
            "����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");

    /**
     * �⌇�������g���ʐ����{�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02321 = errorMgr.defineErrorInfo(
            2321,
            "BUSINESS_ERROR_02321", 
            "�⌇�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");

    /**
     * �⌇����������ʐ����{�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02322 = errorMgr.defineErrorInfo(
            2322,
            "BUSINESS_ERROR_02322", 
            "�⌇����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");

    /**
     * �m�菈�����R�[�h�Ȃ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02323 = errorMgr.defineErrorInfo(
            2323,
            "BUSINESS_ERROR_02323", 
            "�m�菈���������Ȃ���񂪂���܂���B");

    /**
     * ���򒥎��S�����]�̓`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02324 = errorMgr.defineErrorInfo(
            2324,
            "BUSINESS_ERROR_02324", 
            "���򒥎��S�������s�����Ă��܂��B");

    /**
     * ��ʋ敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02325 = errorMgr.defineErrorInfo(
            2325,
            "BUSINESS_ERROR_02325", 
            "��ʋ敪�����w��ł��B");

    /**
     * ���I�������폜�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02326 = errorMgr.defineErrorInfo(
            2326,
            "BUSINESS_ERROR_02326", 
            "���I�������폜�����ł��B");

    /**
     * ���I���������~�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02327 = errorMgr.defineErrorInfo(
            2327,
            "BUSINESS_ERROR_02327", 
            "���I���������~�����ł��B");

    /**
     * ���I�����̃u�b�N�r���f�B���O���Ԃ��I���ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02328 = errorMgr.defineErrorInfo(
            2328,
            "BUSINESS_ERROR_02328", 
            "���I�����̃u�b�N�r���f�B���O���Ԃ��I���ł͂���܂���B");

    /**
     * ���I�����̃X�P�W���[�����ڂ��s�K�؂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02329 = errorMgr.defineErrorInfo(
            2329,
            "BUSINESS_ERROR_02329", 
            "���I�����̃X�P�W���[�����ڂ��s�K�؂ł��B");

    /**
     * ���I�����̌��J���i���ݒ肳��Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02330 = errorMgr.defineErrorInfo(
            2330,
            "BUSINESS_ERROR_02330", 
            "���I�����̌��J���i���ݒ肳��Ă��܂���B");

    /**
     * ���I�����̎戵���ʂ� 0�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02331 = errorMgr.defineErrorInfo(
            2331,
            "BUSINESS_ERROR_02331", 
            "���I�����̎戵���ʂ� 0�ł��B");

    /**
     * ���I�����̍w���\���P�ʂ� 0�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02332 = errorMgr.defineErrorInfo(
            2332,
            "BUSINESS_ERROR_02332", 
            "���I�����̍w���\���P�ʂ� 0�ł��B");

    /**
     * �⌇�f�[�^�����݂��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02333 = errorMgr.defineErrorInfo(
            2333,
            "BUSINESS_ERROR_02333", 
            "�⌇�f�[�^�����݂��܂���B");

    /**
     * ���Z���z�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02334 = errorMgr.defineErrorInfo(
            2334,
            "BUSINESS_ERROR_02334", 
            "���Z���z�����w��ł��B");

    /**
     * ���Z���z��11���𒴂��܂����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02335 = errorMgr.defineErrorInfo(
            2335,
            "BUSINESS_ERROR_02335", 
            "���Z���z��11���𒴂��܂����B");

    /**
     * ��n������c�Ɠ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02336 = errorMgr.defineErrorInfo(
            2336,
            "BUSINESS_ERROR_02336", 
            "��n������c�Ɠ��ł��B");

    /**
     * �抷�攃�t�Œ���z�i�V�K���t�j�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02337 = errorMgr.defineErrorInfo(
            2337,
            "BUSINESS_ERROR_02337", 
            "�抷������̐V�K���t�Œ���z�����𖞂����Ă��܂���B");

    /**
     * �抷�攃�t�Œ���z�i�ǉ����t�j�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02338 = errorMgr.defineErrorInfo(
            2338,
            "BUSINESS_ERROR_02338", 
            "�抷������̒ǉ����t�Œ���z�����𖞂����Ă��܂���B");

    /**
     * �A�������E�ԍώw��f�[�^�擾�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02339 = errorMgr.defineErrorInfo(
            2339,
            "BUSINESS_ERROR_02339", 
            "�O�񒍕��ɂ���茚�̏��A�܂��͕ԍώw��f�[�^�����݂��܂���B");

    /**
     * ���I�m��Ώۃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02340 = errorMgr.defineErrorInfo(
            2340,
            "BUSINESS_ERROR_02340", 
            "���I�m��Ώۂ̕��XID���X�g���擾�ł��܂���B");

    /**
     * ���Z�@@萃R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02341 = errorMgr.defineErrorInfo(
            2341,
            "BUSINESS_ERROR_02341", 
            "���Z�@@萃R�[�h�������ȊO�̒l�ł��B");

    /**
     * ���Z�@@萃R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02342 = errorMgr.defineErrorInfo(
            2342,
            "BUSINESS_ERROR_02342", 
            "���Z�@@萃R�[�h�̕��������s���ł��B");

    /**
     * �e������������̂��߃g���K�[�����ݒ�s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02343 = errorMgr.defineErrorInfo(
            2343,
            "BUSINESS_ERROR_02343", 
            "�e������������̂��߃g���K�[�����ݒ�s�ł��B");

    /**
     * ���Z�҃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02344 = errorMgr.defineErrorInfo(
            2344,
            "BUSINESS_ERROR_02344", 
            "�񋏏Z�҂͔��t�ł��܂���B");

    /**
     * �����ʒm�e�[�u��ID���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02345 = errorMgr.defineErrorInfo(
            2345,
            "BUSINESS_ERROR_02345", 
            "�����ʒm�e�[�u��ID�����w��(null)�܂��͕s���Ȓl�ł��B");

    /**
     * ��s�R�[�h�����̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02346 = errorMgr.defineErrorInfo(
            2346,
            "BUSINESS_ERROR_02346", 
            "��s�R�[�h�������͂ł��B");

    /**
     * ��n�������͓����O�̃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02347 = errorMgr.defineErrorInfo(
            2347,
            "BUSINESS_ERROR_02347", 
            "��n�������͓����O�̓��t�ł��B");

    /**
     * ������敪�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02348 = errorMgr.defineErrorInfo(
            2348,
            "BUSINESS_ERROR_02348", 
            "������敪�̒l���s���ł��B");

    /**
     * FX�������̌����ԍ��ƍX�V�������敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02349 = errorMgr.defineErrorInfo(
            2349,
            "BUSINESS_ERROR_02349", 
            "�X�V��X�e�[�^�X���h�폜�h�̏ꍇ�́AFX�������̌����ԍ��ƍX�V�������敪�������Ɏw��ł��Ȃ��B");

    /**
     * ����������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02350 = errorMgr.defineErrorInfo(
            2350,
            "BUSINESS_ERROR_02350", 
            "����������G���[�B");

    /**
     * �����������ȊO�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02351 = errorMgr.defineErrorInfo(
            2351,
            "BUSINESS_ERROR_02351", 
            "�����������ȊO�̒l�ł��B");

    /**
     * �����󋵂�����`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02352 = errorMgr.defineErrorInfo(
            2352,
            "BUSINESS_ERROR_02352", 
            "�����󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���͎��ԃG���[(��������M����From)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02354 = errorMgr.defineErrorInfo(
            2354,
            "BUSINESS_ERROR_02354", 
            "���͎��ԃG���[(��������M����From)�B");

    /**
     * ���͎��ԃG���[(��������M����To)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02355 = errorMgr.defineErrorInfo(
            2355,
            "BUSINESS_ERROR_02355", 
            "���͎��ԃG���[(��������M����To)�B");

    /**
     * ���͎��ԃG���[(�g���K�[�N������From)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02356 = errorMgr.defineErrorInfo(
            2356,
            "BUSINESS_ERROR_02356", 
            "���͎��ԃG���[(�g���K�[�N������From)�B");

    /**
     * ���͎��ԃG���[(�g���K�[�N������To)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02357 = errorMgr.defineErrorInfo(
            2357,
            "BUSINESS_ERROR_02357", 
            "���͎��ԃG���[(�g���K�[�N������To)�B");

    /**
     * ���͎��ԃG���[(������������From)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02358 = errorMgr.defineErrorInfo(
            2358,
            "BUSINESS_ERROR_02358", 
            "���͎��ԃG���[(������������From)�B");

    /**
     * ���͎��ԃG���[(������������To)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02359 = errorMgr.defineErrorInfo(
            2359,
            "BUSINESS_ERROR_02359", 
            "���͎��ԃG���[(������������To)�B");

    /**
     * �������Ԃ����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02360 = errorMgr.defineErrorInfo(
            2360,
            "BUSINESS_ERROR_02360", 
            "�������Ԃ����w��ł��B");

    /**
     * �������ԃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02361 = errorMgr.defineErrorInfo(
            2361,
            "BUSINESS_ERROR_02361", 
            "�������Ԃ̓��͂��s���ł��B");

    /**
     * ��������From�����w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02362 = errorMgr.defineErrorInfo(
            2362,
            "BUSINESS_ERROR_02362", 
            "��������From�����w��ł��B");

    /**
     * ��������To�����w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02363 = errorMgr.defineErrorInfo(
            2363,
            "BUSINESS_ERROR_02363", 
            "��������To�����w��ł��B");

    /**
     * �������Ԑ������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02364 = errorMgr.defineErrorInfo(
            2364,
            "BUSINESS_ERROR_02364", 
            "�������Ԑ������G���[�B");

    /**
     * ���p�҃R�[�h���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02366 = errorMgr.defineErrorInfo(
            2366,
            "BUSINESS_ERROR_02366", 
            "���p�҃R�[�h���擾�ł��܂���B");

    /**
     * ���p�҃R�[�h�̒l�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02367 = errorMgr.defineErrorInfo(
            2367,
            "BUSINESS_ERROR_02367", 
            "���p�҃R�[�h�̒l�����p�����ȊO�̒l�ł��B");

    /**
     * ���p�҃R�[�h�̒l��8byte�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02368 = errorMgr.defineErrorInfo(
            2368,
            "BUSINESS_ERROR_02368", 
            "���p�҃R�[�h�̒l��8byte�ł͂���܂���B");

    /**
     * ���p�Җ��̕�������120byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02369 = errorMgr.defineErrorInfo(
            2369,
            "BUSINESS_ERROR_02369", 
            "���p�Җ��̕�������120byte���傫���ł��B");

    /**
     * ���O�C���h�c���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02370 = errorMgr.defineErrorInfo(
            2370,
            "BUSINESS_ERROR_02370", 
            "���O�C���h�c���擾�ł��܂���B");

    /**
     * ���O�C���h�c�̒l�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02371 = errorMgr.defineErrorInfo(
            2371,
            "BUSINESS_ERROR_02371", 
            "���O�C���h�c�̒l�����p�����ȊO�̒l�ł��B");

    /**
     * ���O�C���h�c�̒l��8byte�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02372 = errorMgr.defineErrorInfo(
            2372,
            "BUSINESS_ERROR_02372", 
            "���O�C���h�c�̒l��8byte�ł͂���܂���B");

    /**
     * ���Ȏ���敪�̒l��1byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02373 = errorMgr.defineErrorInfo(
            2373,
            "BUSINESS_ERROR_02373", 
            "���Ȏ���敪�̒l��1byte���傫���ł��B");

    /**
     * ���X�J�b�g�敪�̒l��2byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02374 = errorMgr.defineErrorInfo(
            2374,
            "BUSINESS_ERROR_02374", 
            "���X�J�b�g�敪�̒l��2byte���傫���ł��B");

    /**
     * �萔���敪�̒l��2byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02375 = errorMgr.defineErrorInfo(
            2375,
            "BUSINESS_ERROR_02375", 
            "�萔���敪�̒l��2byte���傫���ł��B");

    /**
     * ����\�敪�̒l��1byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02376 = errorMgr.defineErrorInfo(
            2376,
            "BUSINESS_ERROR_02376", 
            "����\�敪�̒l��1byte���傫���ł��B");

    /**
     * ���l���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02377 = errorMgr.defineErrorInfo(
            2377,
            "BUSINESS_ERROR_02377", 
            "���l���擾�ł��܂���B");

    /**
     * ���i�R�[�h�̒l��7byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02378 = errorMgr.defineErrorInfo(
            2378,
            "BUSINESS_ERROR_02378", 
            "���i�R�[�h�̒l��7byte���傫���ł��B");

    /**
     * ����������ʂ̒l�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02379 = errorMgr.defineErrorInfo(
            2379,
            "BUSINESS_ERROR_02379", 
            "����������ʂ̒l�����p�����ȊO�̒l�ł��B");

    /**
     * ����������ʂ̒l��15byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02380 = errorMgr.defineErrorInfo(
            2380,
            "BUSINESS_ERROR_02380", 
            "����������ʂ̒l��15byte���傫���ł��B");

    /**
     * ���p�҃R�[�h�d���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02381 = errorMgr.defineErrorInfo(
            2381,
            "BUSINESS_ERROR_02381", 
            "���p�҃R�[�h�d���G���[�B");

    /**
     * �ȖڃR�[�h�̒l�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02382 = errorMgr.defineErrorInfo(
            2382,
            "BUSINESS_ERROR_02382", 
            "�ȖڃR�[�h�̒l�����p�����ȊO�̒l�ł��B");

    /**
     * �ȖڃR�[�h�̒l��1byte�������ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02383 = errorMgr.defineErrorInfo(
            2383,
            "BUSINESS_ERROR_02383", 
            "�ȖڃR�[�h�̒l��1byte�������ł͂���܂���B");

    /**
     * �o���z�̒l�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02384 = errorMgr.defineErrorInfo(
            2384,
            "BUSINESS_ERROR_02384", 
            "�o���z�̒l�����p�����ȊO�̒l�ł��B");

    /**
     * �o���z�̒l��20byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02385 = errorMgr.defineErrorInfo(
            2385,
            "BUSINESS_ERROR_02385", 
            "�o���z�̒l��20byte���傫���ł��B");

    /**
     * �o�����̒l�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02386 = errorMgr.defineErrorInfo(
            2386,
            "BUSINESS_ERROR_02386", 
            "�o�����̒l�����p�����ȊO�̒l�ł��B");

    /**
     * �o�����̒l��8byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02387 = errorMgr.defineErrorInfo(
            2387,
            "BUSINESS_ERROR_02387", 
            "�o�����̒l��8byte���傫���ł��B");

    /**
     * ���o���ԍ��̒l�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02388 = errorMgr.defineErrorInfo(
            2388,
            "BUSINESS_ERROR_02388", 
            "���o���ԍ��̒l�����p�����ȊO�̒l�ł��B");

    /**
     * ���o���ԍ��̒l��9byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02389 = errorMgr.defineErrorInfo(
            2389,
            "BUSINESS_ERROR_02389", 
            "���o���ԍ��̒l��9byte���傫���ł��B");

    /**
     * �擾�����f�[�^�����݂���ꍇ�A�������d�����ēo�^����Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02390 = errorMgr.defineErrorInfo(
            2390,
            "BUSINESS_ERROR_02390", 
            "�擾�����f�[�^�����݂���ꍇ�A�������d�����ēo�^����Ă��܂��B");

    /**
     * ���I���R�[�h�����݂��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02391 = errorMgr.defineErrorInfo(
            2391,
            "BUSINESS_ERROR_02391", 
            "���I���R�[�h�����݂��܂��B");

    /**
     * �����ɊY������f�[�^�����݂��Ȃ��A�܂��͕������݂��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02392 = errorMgr.defineErrorInfo(
            2392,
            "BUSINESS_ERROR_02392", 
            "�����ɊY������f�[�^�����݂��Ȃ��A�܂��͕������݂��܂��B");

    /**
     * �蓮�����Ώے����Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02393 = errorMgr.defineErrorInfo(
            2393,
            "BUSINESS_ERROR_02393", 
            "�蓮�����Ώے����Ȃ��B");

    /**
     * �����^�C�v�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02394 = errorMgr.defineErrorInfo(
            2394,
            "BUSINESS_ERROR_02394", 
            "�����^�C�v�����w��ł��B");

    /**
     * �����^�C�v������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02395 = errorMgr.defineErrorInfo(
            2395,
            "BUSINESS_ERROR_02395", 
            "�����^�C�v������`�̒l�ł��B");

    /**
     * ����������ʂ����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02396 = errorMgr.defineErrorInfo(
            2396,
            "BUSINESS_ERROR_02396", 
            "����������ʂ����w��ł��B");

    /**
     * ����������ʂ�����`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02397 = errorMgr.defineErrorInfo(
            2397,
            "BUSINESS_ERROR_02397", 
            "����������ʂ�����`�̒l�ł��B");

    /**
     * �O���V�X�e���ڑ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02398 = errorMgr.defineErrorInfo(
            2398,
            "BUSINESS_ERROR_02398", 
            "�O���V�X�e���ڑ��G���[�B");

    /**
     * �ڑ��^�C���A�E�g�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02399 = errorMgr.defineErrorInfo(
            2399,
            "BUSINESS_ERROR_02399", 
            "�ڑ��^�C���A�E�g�G���[�B");

    /**
     * �A����D�揇�ʏd���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02400 = errorMgr.defineErrorInfo(
            2400,
            "BUSINESS_ERROR_02400", 
            "�A����D�揇��1�ʁ`3�ʂɏd�����Ă���A���悪����܂��B");

    /**
     * �g�єԍ��������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02401 = errorMgr.defineErrorInfo(
            2401,
            "BUSINESS_ERROR_02401", 
            "�g�єԍ��������͂ł��B");

    /**
     * �Ζ���d�b�ԍ��������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02402 = errorMgr.defineErrorInfo(
            2402,
            "BUSINESS_ERROR_02402", 
            "�Ζ���d�b�ԍ��������͂ł��B");

    /**
     * ������敪�X�V�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02403 = errorMgr.defineErrorInfo(
            2403,
            "BUSINESS_ERROR_02403", 
            "���݂̖�����敪����w��̖�����敪�֕ύX�ł��܂���B");

    /**
     * FX���O�C��ID���������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02404 = errorMgr.defineErrorInfo(
            2404,
            "BUSINESS_ERROR_02404", 
            "FX���O�C��ID���������擾�ł��܂���B");

    /**
     * FX���O�C��ID�����������p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02405 = errorMgr.defineErrorInfo(
            2405,
            "BUSINESS_ERROR_02405", 
            "FX���O�C��ID�����������p�����ȊO�̒l�ł��B");

    /**
     * FX���O�C��ID�������̒l��2byte�łȂ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02406 = errorMgr.defineErrorInfo(
            2406,
            "BUSINESS_ERROR_02406", 
            "FX���O�C��ID�������̒l��2byte�łȂ��G���[�B");

    /**
     * �ڋq�R�[�h���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02407 = errorMgr.defineErrorInfo(
            2407,
            "BUSINESS_ERROR_02407", 
            "�ڋq�R�[�h���擾�ł��܂���B");

    /**
     * �Y������ڑ��敪���Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02408 = errorMgr.defineErrorInfo(
            2408,
            "BUSINESS_ERROR_02408", 
            "�Y������ڑ��敪���Ȃ��G���[�B");

    /**
     * �A����D�揇�ʐ������`�F�b�N�i�g�сE���̑��j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02409 = errorMgr.defineErrorInfo(
            2409,
            "BUSINESS_ERROR_02409", 
            "�g�єԍ��E���̑��A���悪�����͂̂��߁A�A����D�揇�ʂɁh�g�сE���̑��h��I�����鎖�͏o���܂���B");

    /**
     * �A����D�揇�ʐ������`�F�b�N�i�Ζ���j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02410 = errorMgr.defineErrorInfo(
            2410,
            "BUSINESS_ERROR_02410", 
            "�Ζ���d�b�ԍ��������͂̂��߁A�A����D�揇�ʂɁh�Ζ���h��I�����鎖�͏o���܂���B");

    /**
     * �A����D�揇�ʐ������`�F�b�N�i���̑��A����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02411 = errorMgr.defineErrorInfo(
            2411,
            "BUSINESS_ERROR_02411", 
            "���̑��A���悪�����͂̂��߁A�A����D�揇�ʂɁh���̑��A����h��I�����鎖�͏o���܂���B");

    /**
     * �A����D�揇�ʐ������`�F�b�N�i����ӔC�ҋΖ���j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02412 = errorMgr.defineErrorInfo(
            2412,
            "BUSINESS_ERROR_02412", 
            "����ӔC�ҋΖ��悪�����͂̂��߁A�A����D�揇�ʂɁh����ӔC�ҋΖ���h��I�����鎖�͏o���܂���B");

    /**
     * �o���z�̌�����9�����傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02413 = errorMgr.defineErrorInfo(
            2413,
            "BUSINESS_ERROR_02413", 
            "�o���z�̌�����9�����傫���ł��B");

    /**
     * ���X�R�[�h�A�ڋq�R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02414 = errorMgr.defineErrorInfo(
            2414,
            "BUSINESS_ERROR_02414", 
            "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");

    /**
     * ���[���A�h���X�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02415 = errorMgr.defineErrorInfo(
            2415,
            "BUSINESS_ERROR_02415", 
            "���[���A�h���X���s���ł��B");

    /**
     * �ē����[�����M�t���O�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02416 = errorMgr.defineErrorInfo(
            2416,
            "BUSINESS_ERROR_02416", 
            "�ē����[�����M�t���O���s���ł��B");

    /**
     * �폜�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02417 = errorMgr.defineErrorInfo(
            2417,
            "BUSINESS_ERROR_02417", 
            "�폜�敪���s���ł��B");

    /**
     * ���׍s���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02418 = errorMgr.defineErrorInfo(
            2418,
            "BUSINESS_ERROR_02418", 
            "���R�[�h�������������E�l�𒴂��Ă��܂��B");

    /**
     * �����������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02419 = errorMgr.defineErrorInfo(
            2419,
            "BUSINESS_ERROR_02419", 
            "�w�肳�ꂽ�����͎蓮���������ΏۊO�ł��B");

    /**
     * �����Ώے��������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02420 = errorMgr.defineErrorInfo(
            2420,
            "BUSINESS_ERROR_02420", 
            "�����Ώے��������������͂ł��B");

    /**
     * From����ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02421 = errorMgr.defineErrorInfo(
            2421,
            "BUSINESS_ERROR_02421", 
            "From����ID�������͂ł��B");

    /**
     * To����ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02422 = errorMgr.defineErrorInfo(
            2422,
            "BUSINESS_ERROR_02422", 
            "To����ID�i���j�������͂ł��B");

    /**
     * FX�����J�݃`�F�b�N�G���[(�����J�ݏ������j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02423 = errorMgr.defineErrorInfo(
            2423,
            "BUSINESS_ERROR_02423", 
            "���݁AFX�����J�ݏ������ł��B");

    /**
     * �e�[�u����null�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02424 = errorMgr.defineErrorInfo(
            2424,
            "BUSINESS_ERROR_02424", 
            "�e�[�u������null�ł��B");

    /**
     * �e�[�u��������null�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02425 = errorMgr.defineErrorInfo(
            2425,
            "BUSINESS_ERROR_02425", 
            "�e�[�u����������null�ł��B");

    /**
     * ���������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02426 = errorMgr.defineErrorInfo(
            2426,
            "BUSINESS_ERROR_02426", 
            "���������ɂ͕��X�R�[�h�̑��ɁA1�ȏ���͂��Ă��������B");

    /**
     * �X�V��X�e�[�^�X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02427 = errorMgr.defineErrorInfo(
            2427,
            "BUSINESS_ERROR_02427", 
            "�X�V��X�e�[�^�X��null�ł��B");

    /**
     * �X�����J�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02428 = errorMgr.defineErrorInfo(
            2428,
            "BUSINESS_ERROR_02428", 
            "�X�����J�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ������~�󋵈ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02429 = errorMgr.defineErrorInfo(
            2429,
            "BUSINESS_ERROR_02429", 
            "������~�󋵂������͂ł��B");

    /**
     * ���ꎷ�s�����戵��~ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02430 = errorMgr.defineErrorInfo(
            2430,
            "BUSINESS_ERROR_02430", 
            "���ꎷ�s�����戵��~ID�������͂ł��B");

    /**
     * �戵��~���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02431 = errorMgr.defineErrorInfo(
            2431,
            "BUSINESS_ERROR_02431", 
            "�戵��~��񂪖����͂ł��B");

    /**
     * �������ʂ��擾�ł����ꍇ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02432 = errorMgr.defineErrorInfo(
            2432,
            "BUSINESS_ERROR_02432", 
            "���͂����戵��~���͊��ɓo�^�ςł��B");

    /**
     * �i���i�ʁj���ꎷ�s�����戵��~�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02433 = errorMgr.defineErrorInfo(
            2433,
            "BUSINESS_ERROR_02433", 
            "�w�肳�ꂽ�����t�����ł̏��i�͎戵��~���ł��B");

    /**
     * �i�s��ʁj���ꎷ�s�����戵��~�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02434 = errorMgr.defineErrorInfo(
            2434,
            "BUSINESS_ERROR_02434", 
            "�w�肳�ꂽ�����t�����ł̎s��͎戵��~���ł��B");

    /**
     * �i�����ʁj���ꎷ�s�����戵��~�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02435 = errorMgr.defineErrorInfo(
            2435,
            "BUSINESS_ERROR_02435", 
            "�w�肳�ꂽ�����t�����ł̖����͎戵��~���ł��B");

    /**
     * �؋����������J�݃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02436 = errorMgr.defineErrorInfo(
            2436,
            "BUSINESS_ERROR_02436", 
            "�Y������؋������������݂��Ȃ��B");

    /**
     * �t�@@�C�����e�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02437 = errorMgr.defineErrorInfo(
            2437,
            "BUSINESS_ERROR_02437", 
            "�A�b�v���[�h�t�@@�C���̓��e���s���ł��B");

    /**
     * �f�[�^���R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02438 = errorMgr.defineErrorInfo(
            2438,
            "BUSINESS_ERROR_02438", 
            "�A�b�v���[�h�Ώۂ̃f�[�^�E���R�[�h�����݂��܂���B");

    /**
     * �����f�[�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02439 = errorMgr.defineErrorInfo(
            2439,
            "BUSINESS_ERROR_02439", 
            "���̋@@�\�ɂē����������s���Ă��܂��B�������Ԃ��o���Ă���ēx�u���s�{�^���v�������ĉ������B");

    /**
     * FX�U�։\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02440 = errorMgr.defineErrorInfo(
            2440,
            "BUSINESS_ERROR_02440", 
            "�U�֒�~���G���[�B");

    /**
     * �w����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02441 = errorMgr.defineErrorInfo(
            2441,
            "BUSINESS_ERROR_02441", 
            "�w����ʂ������ȊO�̒l�ł��B");

    /**
     * �w����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02442 = errorMgr.defineErrorInfo(
            2442,
            "BUSINESS_ERROR_02442", 
            "�w����ʂ̃T�C�Y���s���ł��B");

    /**
     * �d���A�h���X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02443 = errorMgr.defineErrorInfo(
            2443,
            "BUSINESS_ERROR_02443", 
            "���[���A�h���X�͊��ɓo�^�ς݂ł��B");

    /**
     * �d���A�h���X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02444 = errorMgr.defineErrorInfo(
            2444,
            "BUSINESS_ERROR_02444", 
            "���[���A�h���X�͈ȉ��̌ڋq�ɂ���ēo�^�ς݂ł��B");

    /**
     * �ٍϋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02445 = errorMgr.defineErrorInfo(
            2445,
            "BUSINESS_ERROR_02445", 
            "������������ٍ͕ϋ敪�̎w��s�B");

    /**
     * �����J�z�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02446 = errorMgr.defineErrorInfo(
            2446,
            "BUSINESS_ERROR_02446", 
            "�����J�z�������ׁ̈A�����s�B");

    /**
     * �ٍϋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02447 = errorMgr.defineErrorInfo(
            2447,
            "BUSINESS_ERROR_02447", 
            "�M�p����ٍ͕ϋ敪�̎w��K�{�B");

    /**
     * ���F�\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02448 = errorMgr.defineErrorInfo(
            2448,
            "BUSINESS_ERROR_02448", 
            "�Ώۂ̃f�[�^�͐R���ςł��B");

    /**
     * �ڋq�����W���o�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02449 = errorMgr.defineErrorInfo(
            2449,
            "BUSINESS_ERROR_02449", 
            "�ڋq�����W���o�^����Ă��܂���B");

    /**
     * ���v�\���󋵕\���쐬�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02450 = errorMgr.defineErrorInfo(
            2450,
            "BUSINESS_ERROR_02450", 
            "���v�\���󋵕\���쐬����Ă��܂���B");

    /**
     * ���v�\���󋵕\�쐬���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02451 = errorMgr.defineErrorInfo(
            2451,
            "BUSINESS_ERROR_02451", 
            "���v�\���󋵕\�͌��ݍ쐬���ł��B");

    /**
     * �����W���́D�����W(��)�D�����W(��)���͒l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02452 = errorMgr.defineErrorInfo(
            2452,
            "BUSINESS_ERROR_02452", 
            "�����W���́D�����W(��)�D�����W(��)�S�Ă������͂ł��B");

    /**
     * �����W���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02453 = errorMgr.defineErrorInfo(
            2453,
            "BUSINESS_ERROR_02453", 
            "�����W���̂��S�p�����ł͂���܂���B");

    /**
     * �����W���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02454 = errorMgr.defineErrorInfo(
            2454,
            "BUSINESS_ERROR_02454", 
            "�����W���̂̕��������s���ł��B");

    /**
     * �����W(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02455 = errorMgr.defineErrorInfo(
            2455,
            "BUSINESS_ERROR_02455", 
            "�����W�i���j�����w��ł��B");

    /**
     * �����W(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02456 = errorMgr.defineErrorInfo(
            2456,
            "BUSINESS_ERROR_02456", 
            "�����W�i���j�������ł͂���܂���B");

    /**
     * �����W(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02457 = errorMgr.defineErrorInfo(
            2457,
            "BUSINESS_ERROR_02457", 
            "�����W�i���j��6���ł͂���܂���B");

    /**
     * �����W(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02458 = errorMgr.defineErrorInfo(
            2458,
            "BUSINESS_ERROR_02458", 
            "�����W�i���j�����w��ł��B");

    /**
     * �����W(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02459 = errorMgr.defineErrorInfo(
            2459,
            "BUSINESS_ERROR_02459", 
            "�����W�i���j�������ł͂���܂���B");

    /**
     * �����W(��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02460 = errorMgr.defineErrorInfo(
            2460,
            "BUSINESS_ERROR_02460", 
            "�����W�i���j��6���ł͂���܂���B");

    /**
     * �����W�l�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02461 = errorMgr.defineErrorInfo(
            2461,
            "BUSINESS_ERROR_02461", 
            "�����W�i���j�̓����W�i���j���傫���ł��B");

    /**
     * �����W�l�d���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02462 = errorMgr.defineErrorInfo(
            2462,
            "BUSINESS_ERROR_02462", 
            "�����W�l���d�����Ă��܂��B");

    /**
     * �R���S���҃R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02463 = errorMgr.defineErrorInfo(
            2463,
            "BUSINESS_ERROR_02463", 
            "�R���S���҃R�[�h�̒l�����p�p�����ȊO�̒l�ł��B");

    /**
     * �������i���j�A�������i���j�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02464 = errorMgr.defineErrorInfo(
            2464,
            "BUSINESS_ERROR_02464", 
            "�������i���j�͔������i���j���傫���ł��B");

    /**
     * ���v�\���󋵕\�쐬�\��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02465 = errorMgr.defineErrorInfo(
            2465,
            "BUSINESS_ERROR_02465", 
            "���v�\���󋵕\�͍쐬�\��Ԃ��G���[�ł��B");

    /**
     * �����W�l�s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02466 = errorMgr.defineErrorInfo(
            2466,
            "BUSINESS_ERROR_02466", 
            "�����W�l���s�����ł��B");

    /**
     * ���[���G���W�����M�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02467 = errorMgr.defineErrorInfo(
            2467,
            "BUSINESS_ERROR_02467", 
            "���[���G���W���ւ̑��M���s�B");

    /**
     * ���[���G���W�����M�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02468 = errorMgr.defineErrorInfo(
            2468,
            "BUSINESS_ERROR_02468", 
            "���[���G���W���ւ̑��M���s�B�l�b�g���[�N�G���[�ł��B");

    /**
     * ���[���G���W�����M�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02469 = errorMgr.defineErrorInfo(
            2469,
            "BUSINESS_ERROR_02469", 
            "���[���G���W���ւ̒����A������s�B�Ή����钍��ID������܂���B");

    /**
     * ���[���G���W�����M�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02470 = errorMgr.defineErrorInfo(
            2470,
            "BUSINESS_ERROR_02470", 
            "���[���G���W���ւ̑��M���s�B����ID���d�����Ă܂��B");

    /**
     * ���[���G���W�����M�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02471 = errorMgr.defineErrorInfo(
            2471,
            "BUSINESS_ERROR_02471", 
            "���[���G���W���ւ̒����A������s�B���ɔ����ς̉\��������܂��B");

    /**
     * ���ʃR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02472 = errorMgr.defineErrorInfo(
            2472,
            "BUSINESS_ERROR_02472", 
            "���ʃR�[�h�̗v�f����0�ł��B");

    /**
     * ���������E���s�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02473 = errorMgr.defineErrorInfo(
            2473,
            "BUSINESS_ERROR_02473", 
            "���������敪���t�w�l�̏ꍇ�́A���s�����ɖ��������w�肵�Ă��������B");

    /**
     * ���M�莞��z���t���ʏ��ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02474 = errorMgr.defineErrorInfo(
            2474,
            "BUSINESS_ERROR_02474", 
            "���t�����ݒ�Ȃ��G���[�B");

    /**
     * ���t���z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02475 = errorMgr.defineErrorInfo(
            2475,
            "BUSINESS_ERROR_02475", 
            "���t���z���̓G���[�B");

    /**
     * ���t���z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02476 = errorMgr.defineErrorInfo(
            2476,
            "BUSINESS_ERROR_02476", 
            "���t���z�i���X�j�������ȊO�̒l�ł��B");

    /**
     * ���t���z�i���X�j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02477 = errorMgr.defineErrorInfo(
            2477,
            "BUSINESS_ERROR_02477", 
            "���t���z�i���X�j�����G���[�B");

    /**
     * ���t���z�i�ςݑ����j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02478 = errorMgr.defineErrorInfo(
            2478,
            "BUSINESS_ERROR_02478", 
            "���t���z�i�ςݑ����j�������ȊO�̒l�ł��B");

    /**
     * ���t���z�i�ςݑ����j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02479 = errorMgr.defineErrorInfo(
            2479,
            "BUSINESS_ERROR_02479", 
            "���t���z�i�ςݑ����j�����G���[�B");

    /**
     * �莞��z���t�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02480 = errorMgr.defineErrorInfo(
            2480,
            "BUSINESS_ERROR_02480", 
            "�莞��z���t�s�����G���[�B");

    /**
     * �莞��z���t�Œ���z�`�F�b�N�A�P�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02481 = errorMgr.defineErrorInfo(
            2481,
            "BUSINESS_ERROR_02481", 
            "�莞��z���t�Œ���z�G���[�B");

    /**
     * �ʊ������ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02482 = errorMgr.defineErrorInfo(
            2482,
            "BUSINESS_ERROR_02482", 
            "�ʊ������Ԃł͂���܂���B");

    /**
     * IPO�\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02483 = errorMgr.defineErrorInfo(
            2483,
            "BUSINESS_ERROR_02483", 
            "�Ώیڋq�͓��I���A�w���\���ς݂ł��B");

    /**
     * IPO�\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02484 = errorMgr.defineErrorInfo(
            2484,
            "BUSINESS_ERROR_02484", 
            "�Ώیڋq�͌J�㓖�I�ς݂ł��B");

    /**
     * IPO�\���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02485 = errorMgr.defineErrorInfo(
            2485,
            "BUSINESS_ERROR_02485", 
            "�\�����R�[�h��SONAR���M�ς݂ł��B");

    /**
     * �\��ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02486 = errorMgr.defineErrorInfo(
            2486,
            "BUSINESS_ERROR_02486", 
            "�\��ID�����w��ł��B");

    /**
     * �ŋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02487 = errorMgr.defineErrorInfo(
            2487,
            "BUSINESS_ERROR_02487", 
            "�ŋ敪�����w��ł��B");

    /**
     * �\�����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02488 = errorMgr.defineErrorInfo(
            2488,
            "BUSINESS_ERROR_02488", 
            "�Y���ڋq�̐\�����R�[�h�͑��݂��܂���B");

    /**
     * �ʊ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02489 = errorMgr.defineErrorInfo(
            2489,
            "BUSINESS_ERROR_02489", 
            "�ʊ����ȊO�ł̓��I�͎���ł��܂���B");

    /**
     * �����������\���ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02490 = errorMgr.defineErrorInfo(
            2490,
            "BUSINESS_ERROR_02490", 
            "�����������\���ʂ𒴉߂��Ă��܂��B");

    /**
     * �莞��z���t�P�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02491 = errorMgr.defineErrorInfo(
            2491,
            "BUSINESS_ERROR_02491", 
            "�莞��z���t�P�ʋ��z�G���[�B");

    /**
     * �����l�����ݒl*�{���ȏォ�ǂ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02492 = errorMgr.defineErrorInfo(
            2492,
            "BUSINESS_ERROR_02492", 
            "���������P�����̓G���[�i�����l���w��̔{�������j�B");

    /**
     * �w�l�̒l�ƁA���������P���ƂŎ��������ݍ���ł��邩�ǂ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02493 = errorMgr.defineErrorInfo(
            2493,
            "BUSINESS_ERROR_02493", 
            "���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�B");

    /**
     * �iW�w�l�j�L����ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02494 = errorMgr.defineErrorInfo(
            2494,
            "BUSINESS_ERROR_02494", 
            "��������W�w�l�����L����Ԃ��ύX�ƂȂ����ׁA�����s�B");

    /**
     * �iW�w�l�j���s�����戵�\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02495 = errorMgr.defineErrorInfo(
            2495,
            "BUSINESS_ERROR_02495", 
            "���͂��ꂽ�iW�w�l�j���s�����͎戵�s�B");

    /**
     * ���~�b�g�����E�����P���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02496 = errorMgr.defineErrorInfo(
            2496,
            "BUSINESS_ERROR_02496", 
            "�v�w�l�����̃��~�b�g�����͐��s�w��s�B");

    /**
     * �w�l�ƁiW�w�l�j�����w�l�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02498 = errorMgr.defineErrorInfo(
            2498,
            "BUSINESS_ERROR_02498", 
            "�����P���ƁiW�w�l�j�����w�l�����l�B");

    /**
     * �v�w�l�p���s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02499 = errorMgr.defineErrorInfo(
            2499,
            "BUSINESS_ERROR_02499", 
            "�v�w�l�p���s���������w��ł��B");

    /**
     * �v�w�l�p���s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02500 = errorMgr.defineErrorInfo(
            2500,
            "BUSINESS_ERROR_02500", 
            "W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");

    /**
     * �v�w�l�p���s�����E�����P���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02501 = errorMgr.defineErrorInfo(
            2501,
            "BUSINESS_ERROR_02501", 
            "�v�w�l�p�����P���敪���h�w�l�h�ȊO�̒l�ł��B");

    /**
     * �v�w�l�p�L����ԋ敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02502 = errorMgr.defineErrorInfo(
            2502,
            "BUSINESS_ERROR_02502", 
            "�v�w�l�L����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �v�w�l�p���s�����E�����L�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02503 = errorMgr.defineErrorInfo(
            2503,
            "BUSINESS_ERROR_02503", 
            "�v�w�l�p���s�������h�������h�ȊO�̒l�ł��B");

    /**
     * �敨�I�v�V���������P�ʂ��f�[�^�s�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02504 = errorMgr.defineErrorInfo(
            2504,
            "BUSINESS_ERROR_02504", 
            "�X�g�b�v�������L���܂��͎����ς̒����͔������������s�B");

    /**
     * �����P��ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02505 = errorMgr.defineErrorInfo(
            2505,
            "BUSINESS_ERROR_02505", 
            "�����P��ID�������͂ł��B");

    /**
     * �����P��ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02506 = errorMgr.defineErrorInfo(
            2506,
            "BUSINESS_ERROR_02506", 
            "�����P��ID�̌������s���ł��B");

    /**
     * �����P��ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02507 = errorMgr.defineErrorInfo(
            2507,
            "BUSINESS_ERROR_02507", 
            "�����P��ID�������ȊO�̒l�ł��B");

    /**
     * ������ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02508 = errorMgr.defineErrorInfo(
            2508,
            "BUSINESS_ERROR_02508", 
            "������Ԃ������ȊO�̒l�ł��B");

    /**
     * �����L����ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02509 = errorMgr.defineErrorInfo(
            2509,
            "BUSINESS_ERROR_02509", 
            "�����L����Ԃ������ȊO�̒l�ł��B");

    /**
     * �Ïؔԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02510 = errorMgr.defineErrorInfo(
            2510,
            "BUSINESS_ERROR_02510", 
            "�Ïؔԍ��������͂ł��B");

    /**
     * �X���b�h�ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02511 = errorMgr.defineErrorInfo(
            2511,
            "BUSINESS_ERROR_02511", 
            "�X���b�h�ԍ������w��ł��B");

    /**
     * �X���b�h�ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02512 = errorMgr.defineErrorInfo(
            2512,
            "BUSINESS_ERROR_02512", 
            "�X���b�h�ԍ��𔼊p�����œ��͂��ĉ������B");

    /**
     * �X���b�h�ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02513 = errorMgr.defineErrorInfo(
            2513,
            "BUSINESS_ERROR_02513", 
            "�X���b�h�ԍ���18���ȉ��œ��͂��ĉ������B");

    /**
     * �X�e�[�^�X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02514 = errorMgr.defineErrorInfo(
            2514,
            "BUSINESS_ERROR_02514", 
            "�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B");

    /**
     * �X�e�[�^�X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02515 = errorMgr.defineErrorInfo(
            2515,
            "BUSINESS_ERROR_02515", 
            "�X�e�[�^�X��1���œ��͂��ĉ������B");

    /**
     * �X�V�X�e�[�^�X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02516 = errorMgr.defineErrorInfo(
            2516,
            "BUSINESS_ERROR_02516", 
            "�X�V�X�e�[�^�X�����w��ł��B");

    /**
     * �X�V�X�e�[�^�X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02517 = errorMgr.defineErrorInfo(
            2517,
            "BUSINESS_ERROR_02517", 
            "�X�V�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B");

    /**
     * �X�V�X�e�[�^�X�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02518 = errorMgr.defineErrorInfo(
            2518,
            "BUSINESS_ERROR_02518", 
            "�X�V�X�e�[�^�X��1���œ��͂��ĉ������B");

    /**
     * ������ԍX�V���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02519 = errorMgr.defineErrorInfo(
            2519,
            "BUSINESS_ERROR_02519", 
            "�S�Ă̕ύX���ڂ����w��ł��B");

    /**
     * �莞��z���t�����{�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02520 = errorMgr.defineErrorInfo(
            2520,
            "BUSINESS_ERROR_02520", 
            "�莞��z���t�����{�G���[�B");

    /**
     * ������ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02521 = errorMgr.defineErrorInfo(
            2521,
            "BUSINESS_ERROR_02521", 
            "��t���^�������^������̒����͐ؑ֏����s�B");

    /**
     * ���͋敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02522 = errorMgr.defineErrorInfo(
            2522,
            "BUSINESS_ERROR_02522", 
            "���͋敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �X�V�Ώۃ��R�[�h�I���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02523 = errorMgr.defineErrorInfo(
            2523,
            "BUSINESS_ERROR_02523", 
            "�X�V�Ώۃ��R�[�h���I������Ă��܂���B");

    /**
     * �莞��z���t�����~�ڋq�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02524 = errorMgr.defineErrorInfo(
            2524,
            "BUSINESS_ERROR_02524", 
            "�莞��z���t�����~�ڋq�G���[�B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02525 = errorMgr.defineErrorInfo(
            2525,
            "BUSINESS_ERROR_02525", 
            "���������敪���g�w��Ȃ��h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");

    /**
     * ���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02526 = errorMgr.defineErrorInfo(
            2526,
            "BUSINESS_ERROR_02526", 
            "���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");

    /**
     * �`�F�b�N�Ώۍ��ڂ̒l�̖����̓`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02527 = errorMgr.defineErrorInfo(
            2527,
            "BUSINESS_ERROR_02527", 
            "�����̔Ԏw�莞�ɁA�ڋq�R�[�h�����͂���Ă��܂��B");

    /**
     * �����Ƃ̊֘A�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02528 = errorMgr.defineErrorInfo(
            2528,
            "BUSINESS_ERROR_02528", 
            "��������n���𒴂��Ă��܂��B");

    /**
     * ���n�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02529 = errorMgr.defineErrorInfo(
            2529,
            "BUSINESS_ERROR_02529", 
            "���n���������w��ł��B");

    /**
     * ���n��n���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02530 = errorMgr.defineErrorInfo(
            2530,
            "BUSINESS_ERROR_02530", 
            "���n��n�������w��ł��B");

    /**
     * ���n���������n��n���𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02531 = errorMgr.defineErrorInfo(
            2531,
            "BUSINESS_ERROR_02531", 
            "���n���������n��n���𒴂��Ă��܂��B");

    /**
     * ������\���Ԃ��߂��܂����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02534 = errorMgr.defineErrorInfo(
            2534,
            "BUSINESS_ERROR_02534", 
            "������\���Ԃ��߂��܂����B");

    /**
     * �������b�N�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02535 = errorMgr.defineErrorInfo(
            2535,
            "BUSINESS_ERROR_02535", 
            "�������b�N���ł��B�������b�N�������Ă��瑀�삵�Ă��������B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02539 = errorMgr.defineErrorInfo(
            2539,
            "BUSINESS_ERROR_02539", 
            "�����Ǝ�����s���Ȋ֌W�ł��B");

    /**
     * �z�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02541 = errorMgr.defineErrorInfo(
            2541,
            "BUSINESS_ERROR_02541", 
            "�z�ʋ��z���Œ�z�ʂ�菬�����ł��B");

    /**
     * �z�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02542 = errorMgr.defineErrorInfo(
            2542,
            "BUSINESS_ERROR_02542", 
            "�z�ʋ��z���ō��z�ʂ𒴂��Ă��܂��B");

    /**
     * �z�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02543 = errorMgr.defineErrorInfo(
            2543,
            "BUSINESS_ERROR_02543", 
            "�z�ʋ��z���\���P�ʂ̐����{�ł͂���܂���B");

    /**
     * �������g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02544 = errorMgr.defineErrorInfo(
            2544,
            "BUSINESS_ERROR_02544", 
            "���łɎ������g�ɒB���Ă��܂��B");

    /**
     * �������g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02545 = errorMgr.defineErrorInfo(
            2545,
            "BUSINESS_ERROR_02545", 
            "�������g�𒴉߂��Ă��܂��B");

    /**
     * �Y���J�X�g�f�B�A�������݂��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02547 = errorMgr.defineErrorInfo(
            2547,
            "BUSINESS_ERROR_02547", 
            "�Y���J�X�g�f�B�A�������݂��܂���B");

    /**
     * ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02549 = errorMgr.defineErrorInfo(
            2549,
            "BUSINESS_ERROR_02549", 
            "��������w��ł��B");

    /**
     * ���P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02551 = errorMgr.defineErrorInfo(
            2551,
            "BUSINESS_ERROR_02551", 
            "���P���͐������S���C�������U���͈̔͊O�ł��B");

    /**
     * ��萔�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02554 = errorMgr.defineErrorInfo(
            2554,
            "BUSINESS_ERROR_02554", 
            "��萔�ʂ�11���ȓ��̐����l�ł͂���܂���B");

    /**
     * �������(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02555 = errorMgr.defineErrorInfo(
            2555,
            "BUSINESS_ERROR_02555", 
            "��������i�O�݁j�̗L���������A�������P�Q���C�������Q���͈̔͊O�ł��B");

    /**
     * �������(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02556 = errorMgr.defineErrorInfo(
            2556,
            "BUSINESS_ERROR_02556", 
            "��������i�O�݁j�����l�ȊO�̒l�ł��B");

    /**
     * �������(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02557 = errorMgr.defineErrorInfo(
            2557,
            "BUSINESS_ERROR_02557", 
            "��������i�O�݁j��0�ȉ��̒l�ł��B");

    /**
     * �������(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02558 = errorMgr.defineErrorInfo(
            2558,
            "BUSINESS_ERROR_02558", 
            "��������i�~�݁j�̃T�C�Y���s���ł��B");

    /**
     * �������(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02559 = errorMgr.defineErrorInfo(
            2559,
            "BUSINESS_ERROR_02559", 
            "��������i�~�݁j�������ȊO�̒l�ł��B");

    /**
     * �������(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02560 = errorMgr.defineErrorInfo(
            2560,
            "BUSINESS_ERROR_02560", 
            "��������i�~�݁j��0�ȉ��̒l�ł��B");

    /**
     * �o�ߗ��q(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02561 = errorMgr.defineErrorInfo(
            2561,
            "BUSINESS_ERROR_02561", 
            "�o�ߗ��q�i�O�݁j�̗L���������A�������P�Q���C�������Q���͈̔͊O�ł��B");

    /**
     * �o�ߗ��q(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02562 = errorMgr.defineErrorInfo(
            2562,
            "BUSINESS_ERROR_02562", 
            "�o�ߗ��q�i�O�݁j�����l�ȊO�̒l�ł��B");

    /**
     * �o�ߗ��q(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02563 = errorMgr.defineErrorInfo(
            2563,
            "BUSINESS_ERROR_02563", 
            "�o�ߗ��q�i�O�݁j��0��菬�����l�ł��B");

    /**
     * �o�ߗ��q(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02564 = errorMgr.defineErrorInfo(
            2564,
            "BUSINESS_ERROR_02564", 
            "�o�ߗ��q�i�~�݁j�̃T�C�Y���s���ł��B");

    /**
     * �o�ߗ��q(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02565 = errorMgr.defineErrorInfo(
            2565,
            "BUSINESS_ERROR_02565", 
            "�o�ߗ��q�i�~�݁j�������ȊO�̒l�ł��B");

    /**
     * �o�ߗ��q(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02566 = errorMgr.defineErrorInfo(
            2566,
            "BUSINESS_ERROR_02566", 
            "�o�ߗ��q�i�~�݁j��0��菬�����l�ł��B");

    /**
     * ��n���(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02567 = errorMgr.defineErrorInfo(
            2567,
            "BUSINESS_ERROR_02567", 
            "��n����i�O�݁j�̗L���������A�������P�P���C�������Q���͈̔͊O�ł��B");

    /**
     * ��n���(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02568 = errorMgr.defineErrorInfo(
            2568,
            "BUSINESS_ERROR_02568", 
            "��n����i�O�݁j�����l�ȊO�̒l�ł��B");

    /**
     * ��n���(�O��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02569 = errorMgr.defineErrorInfo(
            2569,
            "BUSINESS_ERROR_02569", 
            "��n����i�O�݁j��0�ȉ��̒l�ł��B");

    /**
     * ��n���(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02570 = errorMgr.defineErrorInfo(
            2570,
            "BUSINESS_ERROR_02570", 
            "��n����i�~�݁j�̃T�C�Y���s���ł��B");

    /**
     * ��n���(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02571 = errorMgr.defineErrorInfo(
            2571,
            "BUSINESS_ERROR_02571", 
            "��n����i�~�݁j�������ȊO�̒l�ł��B");

    /**
     * ��n���(�~��)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02572 = errorMgr.defineErrorInfo(
            2572,
            "BUSINESS_ERROR_02572", 
            "��n����i�~�݁j��0�ȉ��̒l�ł��B");

    /**
     * �o�ߓ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02573 = errorMgr.defineErrorInfo(
            2573,
            "BUSINESS_ERROR_02573", 
            "�o�ߓ����̃T�C�Y���s���ł��B");

    /**
     * �o�ߓ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02574 = errorMgr.defineErrorInfo(
            2574,
            "BUSINESS_ERROR_02574", 
            "�o�ߓ����������ȊO�̒l�ł��B");

    /**
     * �o�ߓ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02575 = errorMgr.defineErrorInfo(
            2575,
            "BUSINESS_ERROR_02575", 
            "�o�ߓ�����0��菬�����l�ł��B");

    /**
     * ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02576 = errorMgr.defineErrorInfo(
            2576,
            "BUSINESS_ERROR_02576", 
            "������̃T�C�Y���s���ł��B");

    /**
     * ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02577 = errorMgr.defineErrorInfo(
            2577,
            "BUSINESS_ERROR_02577", 
            "������������ȊO�̒l�ł��B");

    /**
     * ������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02578 = errorMgr.defineErrorInfo(
            2578,
            "BUSINESS_ERROR_02578", 
            "�������0��菬�����l�ł��B");

    /**
     * �������b�N�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02580 = errorMgr.defineErrorInfo(
            2580,
            "BUSINESS_ERROR_02580", 
            "�������b�N�敪���h���b�N�����h�A�h���b�N�h�ȊO�̒l�ł��B");

    /**
     * �\���P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02584 = errorMgr.defineErrorInfo(
            2584,
            "BUSINESS_ERROR_02584", 
            "�\���P�ʂ̃T�C�Y���s���ł��B");

    /**
     * �\���P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02585 = errorMgr.defineErrorInfo(
            2585,
            "BUSINESS_ERROR_02585", 
            "�\���P�ʂ������ȊO�̒l�ł��B");

    /**
     * �\���P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02586 = errorMgr.defineErrorInfo(
            2586,
            "BUSINESS_ERROR_02586", 
            "�\���P�ʂ�0�ȉ��̒l�ł��B");

    /**
     * �Œ�z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02587 = errorMgr.defineErrorInfo(
            2587,
            "BUSINESS_ERROR_02587", 
            "�Œ�z�ʂ̃T�C�Y���s���ł��B");

    /**
     * �Œ�z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02588 = errorMgr.defineErrorInfo(
            2588,
            "BUSINESS_ERROR_02588", 
            "�Œ�z�ʂ������ȊO�̒l�ł��B");

    /**
     * �Œ�z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02589 = errorMgr.defineErrorInfo(
            2589,
            "BUSINESS_ERROR_02589", 
            "�Œ�z�ʂ�0��菬�����l�ł��B");

    /**
     * �ō��z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02590 = errorMgr.defineErrorInfo(
            2590,
            "BUSINESS_ERROR_02590", 
            "�ō��z�ʂ̃T�C�Y���s���ł��B");

    /**
     * �ō��z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02591 = errorMgr.defineErrorInfo(
            2591,
            "BUSINESS_ERROR_02591", 
            "�ō��z�ʂ������ȊO�̒l�ł��B");

    /**
     * �ō��z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02592 = errorMgr.defineErrorInfo(
            2592,
            "BUSINESS_ERROR_02592", 
            "�ō��z�ʂ�0��菬�����l�ł��B");

    /**
     * ���t��n���w��l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02593 = errorMgr.defineErrorInfo(
            2593,
            "BUSINESS_ERROR_02593", 
            "���t��n���w��l�̃T�C�Y���s���ł��B");

    /**
     * ���t��n���w��l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02594 = errorMgr.defineErrorInfo(
            2594,
            "BUSINESS_ERROR_02594", 
            "���t��n���w��l�������ȊO�̒l�ł��B");

    /**
     * ���t��n���w��l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02595 = errorMgr.defineErrorInfo(
            2595,
            "BUSINESS_ERROR_02595", 
            "���t��n���w��l���O��菬�����ł��B");

    /**
     * ���p��n���w��l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02596 = errorMgr.defineErrorInfo(
            2596,
            "BUSINESS_ERROR_02596", 
            "���p��n���w��l�̃T�C�Y���s���ł��B");

    /**
     * ���p��n���w��l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02597 = errorMgr.defineErrorInfo(
            2597,
            "BUSINESS_ERROR_02597", 
            "���p��n���w��l�������ȊO�̒l�ł��B");

    /**
     * ���p��n���w��l�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02598 = errorMgr.defineErrorInfo(
            2598,
            "BUSINESS_ERROR_02598", 
            "���p��n���w��l���O��菬�����ł��B");

    /**
     * ����萔�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02599 = errorMgr.defineErrorInfo(
            2599,
            "BUSINESS_ERROR_02599", 
            "����萔�����̗L���������A�������Q���C�������T���͈̔͊O�ł��B");

    /**
     * ����萔�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02600 = errorMgr.defineErrorInfo(
            2600,
            "BUSINESS_ERROR_02600", 
            "����萔���������l�ȊO�̒l�ł��B");

    /**
     * ����萔�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02601 = errorMgr.defineErrorInfo(
            2601,
            "BUSINESS_ERROR_02601", 
            "����萔�������O��菬�����ł��B");

    /**
     * �������g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02602 = errorMgr.defineErrorInfo(
            2602,
            "BUSINESS_ERROR_02602", 
            "�������g�̃T�C�Y���s���ł��B");

    /**
     * �������g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02603 = errorMgr.defineErrorInfo(
            2603,
            "BUSINESS_ERROR_02603", 
            "�������g�������ȊO�̒l�ł��B");

    /**
     * �������g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02604 = errorMgr.defineErrorInfo(
            2604,
            "BUSINESS_ERROR_02604", 
            "�������g��0��菬�����l�ł��B");

    /**
     * �����̔Ԃ̃f�[�^���擾�ł��܂���ł����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02608 = errorMgr.defineErrorInfo(
            2608,
            "BUSINESS_ERROR_02608", 
            "�����̔Ԃ̃f�[�^���擾�ł��܂���ł����B");

    /**
     * �ő�l�𒴂��Ă��Ȃ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02609 = errorMgr.defineErrorInfo(
            2609,
            "BUSINESS_ERROR_02609", 
            "�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B");

    /**
     * �����̔ԑI�����ɁA�ڋq���l�ō������Z�҂��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02610 = errorMgr.defineErrorInfo(
            2610,
            "BUSINESS_ERROR_02610", 
            "�����̔Ԃł��܂���B");

    /**
     * ����s�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02612 = errorMgr.defineErrorInfo(
            2612,
            "BUSINESS_ERROR_02612", 
            "����s�����G���[�B");

    /**
     * ���t�s�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02613 = errorMgr.defineErrorInfo(
            2613,
            "BUSINESS_ERROR_02613", 
            "���t�s�����G���[�B");

    /**
     * ���p�s�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02614 = errorMgr.defineErrorInfo(
            2614,
            "BUSINESS_ERROR_02614", 
            "���p�s�����G���[�B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02616 = errorMgr.defineErrorInfo(
            2616,
            "BUSINESS_ERROR_02616", 
            "���������w��ł��B");

    /**
     * ���s���O�ɔ��p���邱�Ƃ͂ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02618 = errorMgr.defineErrorInfo(
            2618,
            "BUSINESS_ERROR_02618", 
            "���s���O�ɔ��p���邱�Ƃ͂ł��܂���B");

    /**
     * �������敪�𔻒�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02619 = errorMgr.defineErrorInfo(
            2619,
            "BUSINESS_ERROR_02619", 
            "�Ώے����͊��ɖ����ł͂���܂���B");

    /**
     * ���X�v���t�@@�����X�𔻒�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02620 = errorMgr.defineErrorInfo(
            2620,
            "BUSINESS_ERROR_02620", 
            "�������b�N�敪���g�p���镔�X�ł͂���܂���B");

    /**
     * �戵�J�n�������戵�I�������𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02621 = errorMgr.defineErrorInfo(
            2621,
            "BUSINESS_ERROR_02621", 
            "�戵�J�n�������戵�I�������𒴂��Ă��܂��B");

    /**
     * ����J�n��������I�����𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02622 = errorMgr.defineErrorInfo(
            2622,
            "BUSINESS_ERROR_02622", 
            "����J�n��������I�����𒴂��Ă��܂��B");

    /**
     * ����J�n��������J�n��(SONAR)�Ɖ���I����(SONAR)�͈̔͊O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02623 = errorMgr.defineErrorInfo(
            2623,
            "BUSINESS_ERROR_02623", 
            "����J�n��������J�n��(SONAR)�Ɖ���I����(SONAR)�͈̔͊O�ł��B");

    /**
     * ����I����������J�n��(SONAR)�Ɖ���I����(SONAR)�͈̔͊O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02624 = errorMgr.defineErrorInfo(
            2624,
            "BUSINESS_ERROR_02624", 
            "����I����������J�n��(SONAR)�Ɖ���I����(SONAR)�͈̔͊O�ł��B");

    /**
     * �Œ�z�ʂ��ō��z�ʂ𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02625 = errorMgr.defineErrorInfo(
            2625,
            "BUSINESS_ERROR_02625", 
            "�Œ�z�ʂ��ō��z�ʂ𒴂��Ă��܂��B");

    /**
     * �����󋵋敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02626 = errorMgr.defineErrorInfo(
            2626,
            "BUSINESS_ERROR_02626", 
            "�����󋵎w�肪�s���B");

    /**
     * ���ݒl�ƍ�����From�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02627 = errorMgr.defineErrorInfo(
            2627,
            "BUSINESS_ERROR_02627", 
            "���͎��ԃG���[(���ݒl�ƍ�����From)�B");

    /**
     * ���ݒl�ƍ�����To�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02628 = errorMgr.defineErrorInfo(
            2628,
            "BUSINESS_ERROR_02628", 
            "���͎��ԃG���[(���ݒl�ƍ�����To)�B");

    /**
     * ���ݒl�ƍ��敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02629 = errorMgr.defineErrorInfo(
            2629,
            "BUSINESS_ERROR_02629", 
            "���ݒl�ƍ��敪������`�̒l�B");

    /**
     * �����敪�ꗗ�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02630 = errorMgr.defineErrorInfo(
            2630,
            "BUSINESS_ERROR_02630", 
            "�����敪�ꗗ�̗v�f�����O�ł��B");

    /**
     * ��������c�Ɠ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02631 = errorMgr.defineErrorInfo(
            2631,
            "BUSINESS_ERROR_02631", 
            "��������c�Ɠ��ł��B");

    /**
     * ���ύX�\���Ԃ��߂��܂����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02632 = errorMgr.defineErrorInfo(
            2632,
            "BUSINESS_ERROR_02632", 
            "���ύX�\���Ԃ��߂��܂����B");

    /**
     * �������b�N�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02633 = errorMgr.defineErrorInfo(
            2633,
            "BUSINESS_ERROR_02633", 
            "�������b�N�������ł��B�������b�N���Ă��瑀�삵�Ă��������B");

    /**
     * �z�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02634 = errorMgr.defineErrorInfo(
            2634,
            "BUSINESS_ERROR_02634", 
            "�z�ʋ��z�����w��ł��B");

    /**
     * �z�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02635 = errorMgr.defineErrorInfo(
            2635,
            "BUSINESS_ERROR_02635", 
            "�z�ʋ��z�̃T�C�Y���s���ł��B");

    /**
     * �z�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02636 = errorMgr.defineErrorInfo(
            2636,
            "BUSINESS_ERROR_02636", 
            "�z�ʋ��z��0�ȉ��̒l�ł��B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02637 = errorMgr.defineErrorInfo(
            2637,
            "BUSINESS_ERROR_02637", 
            "���������w��ł��B");

    /**
     * �P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02638 = errorMgr.defineErrorInfo(
            2638,
            "BUSINESS_ERROR_02638", 
            "�P���̗L���������������S���C�������U���͈̔͊O�ł��B");

    /**
     * ��p�U��������ύX���g�p�����ԍ��擾�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02639 = errorMgr.defineErrorInfo(
            2639,
            "BUSINESS_ERROR_02639", 
            "���g�p�̐�p�U��������ԍ����擾�ł��܂���B");

    /**
     * ��p�U��������ύX�����ԍ��X�V�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02640 = errorMgr.defineErrorInfo(
            2640,
            "BUSINESS_ERROR_02640", 
            "�����ԍ��͊��Ɏg�p����Ă��܂��B");

    /**
     * �z�ʋ��z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02641 = errorMgr.defineErrorInfo(
            2641,
            "BUSINESS_ERROR_02641", 
            "�z�ʋ��z�������l�ł͂���܂���B");

    /**
     * ����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02642 = errorMgr.defineErrorInfo(
            2642,
            "BUSINESS_ERROR_02642", 
            "����̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02643 = errorMgr.defineErrorInfo(
            2643,
            "BUSINESS_ERROR_02643", 
            "�בփ��[�g�����w��ł��B");

    /**
     * ��p�U��������ύX�����ԍ��擾�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02644 = errorMgr.defineErrorInfo(
            2644,
            "BUSINESS_ERROR_02644", 
            "��p�U��������ԍ������݂��܂���B");

    /**
     * �\������������u���吔�� + ���o���ʁv�͈͓̔��ł��邩�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02645 = errorMgr.defineErrorInfo(
            2645,
            "BUSINESS_ERROR_02645", 
            "�\������������u���吔�� + ���o���ʁv�͈̔͊O�ł��B");

    /**
     * �抷�ȊO�̔������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02646 = errorMgr.defineErrorInfo(
            2646,
            "BUSINESS_ERROR_02646", 
            "���M�����P�ʂ̔����������݂̔�������菬�����l�ł��B");

    /**
     * �抷�ȊO�̔������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02647 = errorMgr.defineErrorInfo(
            2647,
            "BUSINESS_ERROR_02647", 
            "���M�����P�ʂ̔������ƌ��݂̔���������v���Ă��Ȃ��ł��B");

    /**
     * ��d�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02648 = errorMgr.defineErrorInfo(
            2648,
            "BUSINESS_ERROR_02648", 
            "��d�����G���[�B");

    /**
     * ������t�s�̏�ԁB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02649 = errorMgr.defineErrorInfo(
            2649,
            "BUSINESS_ERROR_02649", 
            "������t�s�̏�ԁB");

    /**
     * �O�������s��A���󋵈ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02650 = errorMgr.defineErrorInfo(
            2650,
            "BUSINESS_ERROR_02650", 
            "�O�������s��A���󋵈ꗗ�����w��ł��B");

    /**
     * �o�H�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02651 = errorMgr.defineErrorInfo(
            2651,
            "BUSINESS_ERROR_02651", 
            "�o�H�敪������`�̒l�B");

    /**
     * �o�H�敪�Ǝ�t�敪�̑g�ݍ��킹���s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02652 = errorMgr.defineErrorInfo(
            2652,
            "BUSINESS_ERROR_02652", 
            "�o�H�敪�Ǝ�t�敪�̑g�ݍ��킹���s���B");

    /**
     * �O�������s��A���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02653 = errorMgr.defineErrorInfo(
            2653,
            "BUSINESS_ERROR_02653", 
            "�O�������s��A���敪�����w��ł��B");

    /**
     * �O�������s��A���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02654 = errorMgr.defineErrorInfo(
            2654,
            "BUSINESS_ERROR_02654", 
            "�O�������s��A���敪������`�̒l�B");

    /**
     * �L���[ID�ꗗ�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02656 = errorMgr.defineErrorInfo(
            2656,
            "BUSINESS_ERROR_02656", 
            "�L���[ID�ꗗ�����w��ł��B");

    /**
     * FAX�ԍ����p�����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02658 = errorMgr.defineErrorInfo(
            2658,
            "BUSINESS_ERROR_02658", 
            "FAX�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");

    /**
     * �����J�݂̓��@@�̏ڍו������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02659 = errorMgr.defineErrorInfo(
            2659,
            "BUSINESS_ERROR_02659", 
            "�����J�݂̓��@@�̏ڍׂ̕�������25�i50byte�j���傫���ł��B");

    /**
     * �ʉ݃R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02660 = errorMgr.defineErrorInfo(
            2660,
            "BUSINESS_ERROR_02660", 
            "�ʉ݃R�[�h�����w��(null)�܂��͕s���Ȓl�ł��B");

    /**
     * �����`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02661 = errorMgr.defineErrorInfo(
            2661,
            "BUSINESS_ERROR_02661", 
            "�ڋq�戵�s�����G���[�B");

    /**
     * �������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02662 = errorMgr.defineErrorInfo(
            2662,
            "BUSINESS_ERROR_02662", 
            "����������n���𒴂��Ă��܂��B");

    /**
     * �d�����̈בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02663 = errorMgr.defineErrorInfo(
            2663,
            "BUSINESS_ERROR_02663", 
            "�d�����̈בփ��[�g�̗L���������A�������R���C�������S���͈̔͊O�ł��B");

    /**
     * �d�����̈בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02664 = errorMgr.defineErrorInfo(
            2664,
            "BUSINESS_ERROR_02664", 
            "�d�����̈בփ��[�g�����l�ȊO�̒l�ł��B");

    /**
     * �d�����̈בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02665 = errorMgr.defineErrorInfo(
            2665,
            "BUSINESS_ERROR_02665", 
            "�d�����̈בփ��[�g��0�ȉ��̒l�ł��B");

    /**
     * ���v���׃f�[�^�����݃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02666 = errorMgr.defineErrorInfo(
            2666,
            "BUSINESS_ERROR_02666", 
            "���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B");

    /**
     * �C�O�s��c�Ɠ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02667 = errorMgr.defineErrorInfo(
            2667,
            "BUSINESS_ERROR_02667", 
            "���������C�O�s��c�Ɠ��ł͂���܂���B");

    /**
     * ������ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02668 = errorMgr.defineErrorInfo(
            2668,
            "BUSINESS_ERROR_02668", 
            "������ʂ����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �f�[�^���e�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02669 = errorMgr.defineErrorInfo(
            2669,
            "BUSINESS_ERROR_02669", 
            "�f�[�^���e�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���O�C�����b�N�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02670 = errorMgr.defineErrorInfo(
            2670,
            "BUSINESS_ERROR_02670", 
            "���O�C�����b�N�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * FAX�ԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02671 = errorMgr.defineErrorInfo(
            2671,
            "BUSINESS_ERROR_02671", 
            "�n�C�t���̐����Q�ł͂���܂���B");

    /**
     * �ב֌����̏����Y�c���s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02672 = errorMgr.defineErrorInfo(
            2672,
            "BUSINESS_ERROR_02672", 
            "�����Y�c�����s�����Ă��܂��B");

    /**
     * �ב֌����̌����c���s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02673 = errorMgr.defineErrorInfo(
            2673,
            "BUSINESS_ERROR_02673", 
            "�~�݌����c�����s�����Ă��܂��B");

    /**
     * �ב֌����Ƀ}�C�i�X�ʉ݂���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02674 = errorMgr.defineErrorInfo(
            2674,
            "BUSINESS_ERROR_02674", 
            "�ב֌����ɂ�����ʉݕʌ����c���ŁA�}�C�i�X�̒ʉ݂�����܂��B�ב֌����ŃR���o�[�W������ɍēx�U�ւ��˗����Ă��������B");

    /**
     * �Y�������OP�؋������������݂��Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02675 = errorMgr.defineErrorInfo(
            2675,
            "BUSINESS_ERROR_02675", 
            "�ב֌����ւ̐U�ւł��B���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t�������������q�l���������p�����������Ƃ͂ł��܂���B");

    /**
     * �yFX�z��L�ȊO�Ń��[�U�[�ɋN������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02676 = errorMgr.defineErrorInfo(
            2676,
            "BUSINESS_ERROR_02676", 
            "�U�ւɎ��s���܂����B�i�V�X�e���G���[�j");

    /**
     * �P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02677 = errorMgr.defineErrorInfo(
            2677,
            "BUSINESS_ERROR_02677", 
            "�O�݌��̖����ł͂���܂���B�P���𐮐��ɂ��ĉ������B");

    /**
     * �בփ��[�g�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02678 = errorMgr.defineErrorInfo(
            2678,
            "BUSINESS_ERROR_02678", 
            "�O�݌��̖����ł͂���܂���B�בփ��[�g�͓��͕s�ł��B");

    /**
     * �������������敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02679 = errorMgr.defineErrorInfo(
            2679,
            "BUSINESS_ERROR_02679", 
            "�������������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �ύX���ڂȂ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02680 = errorMgr.defineErrorInfo(
            2680,
            "BUSINESS_ERROR_02680", 
            "�ύX���ڂ�����܂���B");

    /**
     * ����������c�Ɠ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02681 = errorMgr.defineErrorInfo(
            2681,
            "BUSINESS_ERROR_02681", 
            "����������c�Ɠ��ł��B");

    /**
     * ����ӔC�җX�֔ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02682 = errorMgr.defineErrorInfo(
            2682,
            "BUSINESS_ERROR_02682", 
            "����ӔC�җX�֔ԍ���7byte���p�����ł͂���܂���B");

    /**
     * ����ӔC�҉�В��ʔԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02683 = errorMgr.defineErrorInfo(
            2683,
            "BUSINESS_ERROR_02683", 
            "����ӔC�҉�В��ʔԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");

    /**
     * ����ӔC�҉�В��ʔԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02684 = errorMgr.defineErrorInfo(
            2684,
            "BUSINESS_ERROR_02684", 
            "�n�C�t���̐����Q�ł͂���܂���B");

    /**
     * ���̑��A����(�g�сA���)�ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02685 = errorMgr.defineErrorInfo(
            2685,
            "BUSINESS_ERROR_02685", 
            "���̑��A����(�g�сA���)�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");

    /**
     * ���̑��A����(�g�сA���)�ԍ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02686 = errorMgr.defineErrorInfo(
            2686,
            "BUSINESS_ERROR_02686", 
            "�n�C�t���̐����Q�ł͂���܂���B");

    /**
     * ����ӔC�ҔN��+���N�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02687 = errorMgr.defineErrorInfo(
            2687,
            "BUSINESS_ERROR_02687", 
            "����ӔC�� �N��������ӔC�Ґ��N���������w��(null)�܂��͕s���Ȓl�ł��B");

    /**
     * �ύX���ږ����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02688 = errorMgr.defineErrorInfo(
            2688,
            "BUSINESS_ERROR_02688", 
            "�ύX���ږ����G���[�B");

    /**
     * ��n���̔������ȍ~�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02689 = errorMgr.defineErrorInfo(
            2689,
            "BUSINESS_ERROR_02689", 
            "��n���͔������ȍ~�ɂ��ĉ������B");

    /**
     * �������̓����ȍ~�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02690 = errorMgr.defineErrorInfo(
            2690,
            "BUSINESS_ERROR_02690", 
            "�������͓����ȍ~�ɂ��ĉ������B");

    /**
     * �Y�������͈ꕔ��薢�ς̏�ԁB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02691 = errorMgr.defineErrorInfo(
            2691,
            "BUSINESS_ERROR_02691", 
            "�Y�������͈ꕔ��薢�ς̏�ԁB");

    /**
     * �s��z��0���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02692 = errorMgr.defineErrorInfo(
            2692,
            "BUSINESS_ERROR_02692", 
            "�s��z��0���ł��B");

    /**
     * �����̓����ȑO�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02693 = errorMgr.defineErrorInfo(
            2693,
            "BUSINESS_ERROR_02693", 
            "���������������z���Ă��܂��B");

    /**
     * �ژ_�����{���敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02694 = errorMgr.defineErrorInfo(
            2694,
            "BUSINESS_ERROR_02694", 
            "�ژ_�����{���敪���s���ȃR�[�h�l�ł��B");

    /**
     * �����F�̐\�����݃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02695 = errorMgr.defineErrorInfo(
            2695,
            "BUSINESS_ERROR_02695", 
            "���꒍���ɑ΂��関���F�̐\�������݂��܂��B");

    /**
     * ������ʂ���̏��F�҂ɂ�銮�����N�G�X�g���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02696 = errorMgr.defineErrorInfo(
            2696,
            "BUSINESS_ERROR_02696", 
            "�R���v���C�A���X�R���󋵏Ɖ��ʂ�����s���Ă��������B");

    /**
     * �R���f�[�^�̒�����Ԃ�����ρA���F�ρA�۔F�ς̏ꍇ�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02697 = errorMgr.defineErrorInfo(
            2697,
            "BUSINESS_ERROR_02697", 
            "�X�V�ς݂̂��߁A��t�ł��܂���B");

    /**
     * �R���f�[�^�̒�����ԂƕύX���钍����Ԃ�����ł���ꍇ�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02698 = errorMgr.defineErrorInfo(
            2698,
            "BUSINESS_ERROR_02698", 
            "�����ς݂̂��߁A��t�ł��܂���B");

    /**
     * ���F�҂܂��͐\���҂ł��邱�Ƃ̃`�F�b�N���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02699 = errorMgr.defineErrorInfo(
            2699,
            "BUSINESS_ERROR_02699", 
            "�R���v���C�A���X�����̌���������܂���B");

    /**
     * �������̖����ȍ~�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02700 = errorMgr.defineErrorInfo(
            2700,
            "BUSINESS_ERROR_02700", 
            "�������͖����ȍ~�ɂ��ĉ������B");

    /**
     * �����󋵋敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02701 = errorMgr.defineErrorInfo(
            2701,
            "BUSINESS_ERROR_02701", 
            "W�w�l�����͔����󋵋敪�F�����R���G���[�w��s�B");

    /**
     * �擾�����s�ꂪnull�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02702 = errorMgr.defineErrorInfo(
            2702,
            "BUSINESS_ERROR_02702", 
            "�D��s�ꂪ���w��ł��B");

    /**
     * �E�v�̍ő�T�C�Y�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02703 = errorMgr.defineErrorInfo(
            2703,
            "BUSINESS_ERROR_02703", 
            "�E�v���ő�T�C�Y�𒴂��Ă��܂��B");

    /**
     * �����P�ʃe�[�u���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02704 = errorMgr.defineErrorInfo(
            2704,
            "BUSINESS_ERROR_02704", 
            "�����P�ʃe�[�u�������I���ł��B");

    /**
     * �����P�ʃe�[�u���敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02705 = errorMgr.defineErrorInfo(
            2705,
            "BUSINESS_ERROR_02705", 
            "�����P�ʃe�[�u�������݂��Ȃ��敪�ł��B");

    /**
     * �Ɖ�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02706 = errorMgr.defineErrorInfo(
            2706,
            "BUSINESS_ERROR_02706", 
            "�Ɖ�s�B");

    /**
     * ���s�P����null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02707 = errorMgr.defineErrorInfo(
            2707,
            "BUSINESS_ERROR_02707", 
            "���s�P����null�B");

    /**
     * ���Z�Ґ\���݃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02708 = errorMgr.defineErrorInfo(
            2708,
            "BUSINESS_ERROR_02708", 
            "�񋏏Z�҂͐\���ނ��Ƃ��ł��܂���B");

    /**
     * �L�����y�[�����̂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02709 = errorMgr.defineErrorInfo(
            2709,
            "BUSINESS_ERROR_02709", 
            "�L�����y�[�����̌����G���[�B");

    /**
     * �X�V�����t���O�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02710 = errorMgr.defineErrorInfo(
            2710,
            "BUSINESS_ERROR_02710", 
            "�X�V�����t���O�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �o�^�^�C�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02711 = errorMgr.defineErrorInfo(
            2711,
            "BUSINESS_ERROR_02711", 
            "�o�^�^�C�v��'1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̒l�ł��B");

    /**
     * �L�����y�[�����̂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02712 = errorMgr.defineErrorInfo(
            2712,
            "BUSINESS_ERROR_02712", 
            "�L�����y�[�����̖����̓G���[�B");

    /**
     * �Ώۊ���From�ATo�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02713 = errorMgr.defineErrorInfo(
            2713,
            "BUSINESS_ERROR_02713", 
            "�Ώۊ��Ԗ����̓G���[�B");

    /**
     * �Ώۊ���From�ATo�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02714 = errorMgr.defineErrorInfo(
            2714,
            "BUSINESS_ERROR_02714", 
            "�Ώۊ��ԓ��t�����݃G���[�B");

    /**
     * �Ώۊ���From�ATo�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02715 = errorMgr.defineErrorInfo(
            2715,
            "BUSINESS_ERROR_02715", 
            "�Ώۊ��ԃG���[�B");

    /**
     * �萔�������L�����y�[������ID�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02716 = errorMgr.defineErrorInfo(
            2716,
            "BUSINESS_ERROR_02716", 
            "�萔�������L�����y�[������ID�����w��ł��B");

    /**
     * �Ώۊ���To�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02717 = errorMgr.defineErrorInfo(
            2717,
            "BUSINESS_ERROR_02717", 
            "�Ώۊ���To�̓��t�����݂̓��t���ߋ����t�ł��B");

    /**
     * ���i�R�[�h�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02718 = errorMgr.defineErrorInfo(
            2718,
            "BUSINESS_ERROR_02718", 
            "���i���I���G���[�B");

    /**
     * �����J�݋敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02719 = errorMgr.defineErrorInfo(
            2719,
            "BUSINESS_ERROR_02719", 
            "�����J�݋敪�G���[�B");

    /**
     * �����J�ݓ��̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02720 = errorMgr.defineErrorInfo(
            2720,
            "BUSINESS_ERROR_02720", 
            "�����J�ݓ��G���[�B");

    /**
     * �X�V�����t���O�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02721 = errorMgr.defineErrorInfo(
            2721,
            "BUSINESS_ERROR_02721", 
            "�X�V�����t���O��'0' �� '1'�ȊO�̒l�ł��B");

    /**
     * �o�^�^�C�v�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02722 = errorMgr.defineErrorInfo(
            2722,
            "BUSINESS_ERROR_02722", 
            "�o�^�^�C�v��'0'�ȊO�̒l�ł��B");

    /**
     * ���ɕύX������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02723 = errorMgr.defineErrorInfo(
            2723,
            "BUSINESS_ERROR_02723", 
            "���ɕύX������܂���B");

    /**
     * �L�����y�[�������ɑ��݂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02724 = errorMgr.defineErrorInfo(
            2724,
            "BUSINESS_ERROR_02724", 
            "�L�����y�[�������ɑ��݂���B");

    /**
     * �L�����y�[�����̓��̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02725 = errorMgr.defineErrorInfo(
            2725,
            "BUSINESS_ERROR_02725", 
            "�L�����y�[�����̓��̓G���[�B");

    /**
     * �L�����y�[�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02726 = errorMgr.defineErrorInfo(
            2726,
            "BUSINESS_ERROR_02726", 
            "�L�����y�[�������͊��ɓo�^�ς݂ł��B");

    /**
     * �L�����y�[���͊��ɍ폜����Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02727 = errorMgr.defineErrorInfo(
            2727,
            "BUSINESS_ERROR_02727", 
            "�L�����y�[���͊��ɍ폜����Ă��܂��B");

    /**
     * ���X�R�[�h�̗v�f���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02728 = errorMgr.defineErrorInfo(
            2728,
            "BUSINESS_ERROR_02728", 
            "���X�R�[�h�̗v�f����1�ȊO�ł��B");

    /**
     * ��n���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02729 = errorMgr.defineErrorInfo(
            2729,
            "BUSINESS_ERROR_02729", 
            "��n���������ȊO�̒l�ł��B");

    /**
     * ��n���G���[(���݂��Ȃ����t)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02730 = errorMgr.defineErrorInfo(
            2730,
            "BUSINESS_ERROR_02730", 
            "��n���G���[(���݂��Ȃ����t)�B");

    /**
     * �i�O�j�_�񏑖������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02731 = errorMgr.defineErrorInfo(
            2731,
            "BUSINESS_ERROR_02731", 
            "�i�O�j�_�񏑖������G���[�B");

    /**
     * �O��MMF��n���@@�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02732 = errorMgr.defineErrorInfo(
            2732,
            "BUSINESS_ERROR_02732", 
            "�O��MMF��n���@@�`�F�b�N�G���[�B");

    /**
     * �O��MMF��d�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02733 = errorMgr.defineErrorInfo(
            2733,
            "BUSINESS_ERROR_02733", 
            "�O��MMF��d�����G���[�B");

    /**
     * �L�����y�[�����{���J�n���t�ύX�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02734 = errorMgr.defineErrorInfo(
            2734,
            "BUSINESS_ERROR_02734", 
            "�L�����y�[�����{���̑Ώۊ���From �y�� �����J�ݓ�From�̕ύX�͂ł��܂���B");

    /**
     * �ߋ����ԃf�[�^�ύX�E�폜�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02735 = errorMgr.defineErrorInfo(
            2735,
            "BUSINESS_ERROR_02735", 
            "�ߋ����Ԃ̃f�[�^�͕ύX�E�폜�ł��܂���B");

    /**
     * �����J�ݓ�To�ݒ�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02736 = errorMgr.defineErrorInfo(
            2736,
            "BUSINESS_ERROR_02736", 
            "�����J�ݓ�To���ߋ����t�ł��B");

    /**
     * �����J�ݓ�To�ݒ�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02737 = errorMgr.defineErrorInfo(
            2737,
            "BUSINESS_ERROR_02737", 
            "�����J�ݓ�To�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n�����ߋ��̓��t�ł��B");

    /**
     * �����J�ݓ�To�ݒ�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02738 = errorMgr.defineErrorInfo(
            2738,
            "BUSINESS_ERROR_02738", 
            "�����J�ݓ�To���K�p�J�n�����ߋ��̓��t�ł��B");

    /**
     * �����J�ݓ�From�ݒ�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02739 = errorMgr.defineErrorInfo(
            2739,
            "BUSINESS_ERROR_02739", 
            "�����J�ݓ�From�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n���ȑO�̓��t�ł��B");

    /**
     * ���Ԏw��To�ݒ�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02740 = errorMgr.defineErrorInfo(
            2740,
            "BUSINESS_ERROR_02740", 
            "���Ԏw��To���K�p�J�n�����ߋ��̓��t�ł��B");

    /**
     * ���Ԏw��To�ݒ�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02741 = errorMgr.defineErrorInfo(
            2741,
            "BUSINESS_ERROR_02741", 
            "���Ԏw��To�������J�ݓ�To�ȑO�̓��t�ł��B");

    /**
     * �����J�ݓ�From�ݒ�l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02742 = errorMgr.defineErrorInfo(
            2742,
            "BUSINESS_ERROR_02742", 
            "�����J�ݓ�From �͌��݂��ߋ����t�ɂ͐ݒ�ł��܂���B");

    /**
     * �����J�ݓ�From�ߋ����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02744 = errorMgr.defineErrorInfo(
            2744,
            "BUSINESS_ERROR_02744", 
            "�����J�ݓ�From���ߋ����t�̃f�[�^�͕ύX�ł��܂���B");

    /**
     * �O�ݍŒ���z�i�V�K���t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02745 = errorMgr.defineErrorInfo(
            2745,
            "BUSINESS_ERROR_02745", 
            "�O�ݍŒ���z�i�V�K���t�j�G���[�B");

    /**
     * �O�ݒP�ʋ��z�i�V�K���t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02746 = errorMgr.defineErrorInfo(
            2746,
            "BUSINESS_ERROR_02746", 
            "�O�ݒP�ʋ��z�i�V�K���t�j�G���[�B");

    /**
     * �O�ݍŒ���z�i�ǉ����t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02747 = errorMgr.defineErrorInfo(
            2747,
            "BUSINESS_ERROR_02747", 
            "�O�ݍŒ���z�i�ǉ����t�j�G���[�B");

    /**
     * �O�ݒP�ʋ��z�i�ǉ����t�j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02748 = errorMgr.defineErrorInfo(
            2748,
            "BUSINESS_ERROR_02748", 
            "�O�ݒP�ʋ��z�i�ǉ����t�j�G���[�B");

    /**
     * �O�ݍŒ���z�i���j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02749 = errorMgr.defineErrorInfo(
            2749,
            "BUSINESS_ERROR_02749", 
            "�O�ݍŒ���z�i���j�G���[�B");

    /**
     * �O�ݒP�ʋ��z�i���j�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02750 = errorMgr.defineErrorInfo(
            2750,
            "BUSINESS_ERROR_02750", 
            "�O�ݒP�ʋ��z�i���j�G���[�B");

    /**
     * �U����i��s�����j�o�^�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02751 = errorMgr.defineErrorInfo(
            2751,
            "BUSINESS_ERROR_02751", 
            "�U����i��s�����j�o�^���~�ݓo�^�ł͂���܂���B");

    /**
     * �����P�ʂ��擾�o���Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02752 = errorMgr.defineErrorInfo(
            2752,
            "BUSINESS_ERROR_02752", 
            "�����P�ʂ��擾�o���Ȃ��B");

    /**
     * ���F��ԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02753 = errorMgr.defineErrorInfo(
            2753,
            "BUSINESS_ERROR_02753", 
            "���F��Ԃ�����`�̒l�B");

    /**
     * �쐬����From/To�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02754 = errorMgr.defineErrorInfo(
            2754,
            "BUSINESS_ERROR_02754", 
            "���͓��t�G���[�i�쐬����From�j�B");

    /**
     * �쐬����From/To�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02755 = errorMgr.defineErrorInfo(
            2755,
            "BUSINESS_ERROR_02755", 
            "���͓��t�G���[�i�쐬����To�j�B");

    /**
     * ���F����From/To�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02756 = errorMgr.defineErrorInfo(
            2756,
            "BUSINESS_ERROR_02756", 
            "���͓��t�G���[�i���F����From�j�B");

    /**
     * ���F����From/To�������`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02757 = errorMgr.defineErrorInfo(
            2757,
            "BUSINESS_ERROR_02757", 
            "���͓��t�G���[�i���F����To�j�B");

    /**
     * �������ϗ��R�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02758 = errorMgr.defineErrorInfo(
            2758,
            "BUSINESS_ERROR_02758", 
            "�������ϗ��R������`�̒l�B");

    /**
     * �����G���[���R�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02759 = errorMgr.defineErrorInfo(
            2759,
            "BUSINESS_ERROR_02759", 
            "�����G���[���R�R�[�h������`�̒l�B");

    /**
     * ���F�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02760 = errorMgr.defineErrorInfo(
            2760,
            "BUSINESS_ERROR_02760", 
            "���F�敪������`�̒l�B");

    /**
     * ���F�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02761 = errorMgr.defineErrorInfo(
            2761,
            "BUSINESS_ERROR_02761", 
            "���F�敪��null�B");

    /**
     * �������Ϗ����敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02762 = errorMgr.defineErrorInfo(
            2762,
            "BUSINESS_ERROR_02762", 
            "�������Ϗ����敪��null�B");

    /**
     * �w�l�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02763 = errorMgr.defineErrorInfo(
            2763,
            "BUSINESS_ERROR_02763", 
            "�w�l�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���t�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02764 = errorMgr.defineErrorInfo(
            2764,
            "BUSINESS_ERROR_02764", 
            "���t�P�������w��ł��B");

    /**
     * ���t�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02765 = errorMgr.defineErrorInfo(
            2765,
            "BUSINESS_ERROR_02765", 
            "���t�P���̗L���������A�������S���C�������U���͈̔͊O�ł��B");

    /**
     * ���t�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02766 = errorMgr.defineErrorInfo(
            2766,
            "BUSINESS_ERROR_02766", 
            "���t�P�������l�ȊO�̒l�ł��B");

    /**
     * ���t�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02767 = errorMgr.defineErrorInfo(
            2767,
            "BUSINESS_ERROR_02767", 
            "���t�P����0�ȉ��̒l�ł��B");

    /**
     * �����t���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02768 = errorMgr.defineErrorInfo(
            2768,
            "BUSINESS_ERROR_02768", 
            "�����t���O�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �בփ��X�N�t���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02769 = errorMgr.defineErrorInfo(
            2769,
            "BUSINESS_ERROR_02769", 
            "�בփ��X�N�t���O�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���p�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02770 = errorMgr.defineErrorInfo(
            2770,
            "BUSINESS_ERROR_02770", 
            "���p�P�������w��ł��B");

    /**
     * ���p�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02771 = errorMgr.defineErrorInfo(
            2771,
            "BUSINESS_ERROR_02771", 
            "���p�P���̗L���������A�������S���C�������U���͈̔͊O�ł��B");

    /**
     * ���p�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02772 = errorMgr.defineErrorInfo(
            2772,
            "BUSINESS_ERROR_02772", 
            "���p�P�������l�ȊO�̒l�ł��B");

    /**
     * ���p�P���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02773 = errorMgr.defineErrorInfo(
            2773,
            "BUSINESS_ERROR_02773", 
            "���p�P����0�ȉ��̒l�ł��B");

    /**
     * �������Ϗ����敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02774 = errorMgr.defineErrorInfo(
            2774,
            "BUSINESS_ERROR_02774", 
            "�������Ϗ����敪������`�̒l�B");

    /**
     * �،���ЃR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02775 = errorMgr.defineErrorInfo(
            2775,
            "BUSINESS_ERROR_02775", 
            "�،���ЃR�[�h��null�B");

    /**
     * ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02776 = errorMgr.defineErrorInfo(
            2776,
            "BUSINESS_ERROR_02776", 
            "ID��null�B");

    /**
     * ���t�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02777 = errorMgr.defineErrorInfo(
            2777,
            "BUSINESS_ERROR_02777", 
            "���t�G���[�B");

    /**
     * �A����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02778 = errorMgr.defineErrorInfo(
            2778,
            "BUSINESS_ERROR_02778", 
            "�A����ʂ����p�p�����ȊO�̒l�ł��B");

    /**
     * ���N�G�X�g�����w��(null)�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02779 = errorMgr.defineErrorInfo(
            2779,
            "BUSINESS_ERROR_02779", 
            "���N�G�X�g�����w��(null)�ł��B");

    /**
     * ������Ԃ̏����o�^���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02780 = errorMgr.defineErrorInfo(
            2780,
            "BUSINESS_ERROR_02780", 
            "������Ԃ̏����o�^���e�Ɍ�肪����܂��B");

    /**
     * �R���T�[�r�XID�̏����o�^���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02781 = errorMgr.defineErrorInfo(
            2781,
            "BUSINESS_ERROR_02781", 
            "�R���T�[�r�XID�̏����o�^���e�Ɍ�肪����܂��B");

    /**
     * ���҃R�[�h�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02782 = errorMgr.defineErrorInfo(
            2782,
            "BUSINESS_ERROR_02782", 
            "���҃R�[�h�G���[�B");

    /**
     * �ڋq�_�u���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02783 = errorMgr.defineErrorInfo(
            2783,
            "BUSINESS_ERROR_02783", 
            "�ڋq�_�u���G���[�B");

    /**
     * �폜�Y�����R�[�h�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02784 = errorMgr.defineErrorInfo(
            2784,
            "BUSINESS_ERROR_02784", 
            "�폜�Y�����R�[�h�Ȃ��B");

    /**
     * �����R�[�h�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02785 = errorMgr.defineErrorInfo(
            2785,
            "BUSINESS_ERROR_02785", 
            "�����R�[�h�G���[�B");

    /**
     * ���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02786 = errorMgr.defineErrorInfo(
            2786,
            "BUSINESS_ERROR_02786", 
            "���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");

    /**
     * �`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02787 = errorMgr.defineErrorInfo(
            2787,
            "BUSINESS_ERROR_02787", 
            "�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");

    /**
     * ���Z�@@�փR�[�h���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02788 = errorMgr.defineErrorInfo(
            2788,
            "BUSINESS_ERROR_02788", 
            "���Z�@@�փR�[�h���w��G���[�B");

    /**
     * �x�X�R�[�h���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02789 = errorMgr.defineErrorInfo(
            2789,
            "BUSINESS_ERROR_02789", 
            "�x�X�R�[�h���w��G���[�B");

    /**
     * ���i�敪�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02790 = errorMgr.defineErrorInfo(
            2790,
            "BUSINESS_ERROR_02790", 
            "���i�敪�G���[�B");

    /**
     * �U�o�͈̓G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02791 = errorMgr.defineErrorInfo(
            2791,
            "BUSINESS_ERROR_02791", 
            "�U�o�͈̓G���[�B");

    /**
     * ���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02792 = errorMgr.defineErrorInfo(
            2792,
            "BUSINESS_ERROR_02792", 
            "���i�����p�p�����ȊO�̒l�ł��B");

    /**
     * ���i�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02793 = errorMgr.defineErrorInfo(
            2793,
            "BUSINESS_ERROR_02793", 
            "���i�̃T�C�Y���s���ł��B");

    /**
     * �w��敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02794 = errorMgr.defineErrorInfo(
            2794,
            "BUSINESS_ERROR_02794", 
            "�w��敪�����p�p�����ȊO�̒l�ł��B");

    /**
     * �w��敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02795 = errorMgr.defineErrorInfo(
            2795,
            "BUSINESS_ERROR_02795", 
            "�w��敪�̃T�C�Y���s���ł��B");

    /**
     * ���M�������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02796 = errorMgr.defineErrorInfo(
            2796,
            "BUSINESS_ERROR_02796", 
            "���M�������擾�ł��܂���B");

    /**
     * ���������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02797 = errorMgr.defineErrorInfo(
            2797,
            "BUSINESS_ERROR_02797", 
            "���������擾�ł��܂���B");

    /**
     * �I�����C�����܂��͓`�[���쐬�܂��͓`�[���M�ς݂̏ꍇ�A����s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02798 = errorMgr.defineErrorInfo(
            2798,
            "BUSINESS_ERROR_02798", 
            "�I�����C�����܂��͓`�[���쐬�܂��͓`�[���M�ς݂̏ꍇ�A����s�B");

    /**
     * �����\���s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02799 = errorMgr.defineErrorInfo(
            2799,
            "BUSINESS_ERROR_02799", 
            "���Y�T�[�r�X�ւ̖����\�����s�����Ƃ��ł��܂���B");

    /**
     * �L���\���s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02800 = errorMgr.defineErrorInfo(
            2800,
            "BUSINESS_ERROR_02800", 
            "�����̊��ԓ��Y�T�[�r�X�ւ̖����\�����\�ł��B");

    /**
     * �\���s�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02801 = errorMgr.defineErrorInfo(
            2801,
            "BUSINESS_ERROR_02801", 
            "���Y�T�[�r�X�ւ̂��\���͂ł��܂���B");

    /**
     * �����Ώۊ��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02802 = errorMgr.defineErrorInfo(
            2802,
            "BUSINESS_ERROR_02802", 
            "�����Ώۊ��Ԃ����l�ȊO�̒l�ł��B");

    /**
     * �����Ώۊ��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02803 = errorMgr.defineErrorInfo(
            2803,
            "BUSINESS_ERROR_02803", 
            "�����Ώۊ��Ԃ̃T�C�Y���s���ł��B");

    /**
     * �K�p�I�����ߋ����t�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02804 = errorMgr.defineErrorInfo(
            2804,
            "BUSINESS_ERROR_02804", 
            "�K�p�I�����ߋ����t�G���[�B");

    /**
     * �T�[�r�X�\���������̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02805 = errorMgr.defineErrorInfo(
            2805,
            "BUSINESS_ERROR_02805", 
            "�T�[�r�X�\���������null�A�܂��͐\�������敪��'2'(�\���s��)�ł��B");

    /**
     * �����Ώۊ��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02806 = errorMgr.defineErrorInfo(
            2806,
            "BUSINESS_ERROR_02806", 
            "�����Ώۊ��Ԃ����w��ł��B");

    /**
     * �\�������敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02807 = errorMgr.defineErrorInfo(
            2807,
            "BUSINESS_ERROR_02807", 
            "�\�������敪��'1'(�����Ώ�)�A�܂���'2'(�\���s��)�ł��B");

    /**
     * �e�������������ϒ����̂��߃g���K�[�����ݒ�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02808 = errorMgr.defineErrorInfo(
            2808,
            "BUSINESS_ERROR_02808", 
            "�e�������������ϒ����̂��߃g���K�[�����ݒ�s�B");

    /**
     * ���҂̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02809 = errorMgr.defineErrorInfo(
            2809,
            "BUSINESS_ERROR_02809", 
            "���҈ȊO�͎蓮�������ϕs�B");

    /**
     * �����\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02810 = errorMgr.defineErrorInfo(
            2810,
            "BUSINESS_ERROR_02810", 
            "�㗝���͎҂́A�����F�̋������ϒ����͒����ł��܂���B");

    /**
     * �����\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02811 = errorMgr.defineErrorInfo(
            2811,
            "BUSINESS_ERROR_02811", 
            "�������ϒ����͒����ł��܂���B");

    /**
     * �蓮�������σt���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02812 = errorMgr.defineErrorInfo(
            2812,
            "BUSINESS_ERROR_02812", 
            "�蓮�������ς͈ꊇ���ϕs�B");

    /**
     * �蓮�������σt���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02813 = errorMgr.defineErrorInfo(
            2813,
            "BUSINESS_ERROR_02813", 
            "�蓮�������ς͏o����܂Œ����w��s�B");

    /**
     * �蓮�������σt���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02814 = errorMgr.defineErrorInfo(
            2814,
            "BUSINESS_ERROR_02814", 
            "�蓮�������ς͔��������w��s�B");

    /**
     * ���i�̒���No�ɑΉ�����R���󋵃f�[�^�̑��݃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02815 = errorMgr.defineErrorInfo(
            2815,
            "BUSINESS_ERROR_02815", 
            "���i�̒���No�ɑΉ�����R���󋵃f�[�^�����݂��܂���B");

    /**
     * �[��܂Œ����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02816 = errorMgr.defineErrorInfo(
            2816,
            "BUSINESS_ERROR_02816", 
            "�[��܂Œ����͎�舵���܂���B");

    /**
     * ���������E���s�����̃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02817 = errorMgr.defineErrorInfo(
            2817,
            "BUSINESS_ERROR_02817", 
            "���������敪���g3�F�[��܂Œ����h�̏ꍇ�́A���s�����Ɂg1�F�������h��ݒ肵�ĉ������B");

    /**
     * �����̃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02818 = errorMgr.defineErrorInfo(
            2818,
            "BUSINESS_ERROR_02818", 
            "���������敪���g3�F�[��܂Œ����h�̏ꍇ�́A�����L�������w��s�ł��B");

    /**
     * ���q�l���ʔԍ��s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02819 = errorMgr.defineErrorInfo(
            2819,
            "BUSINESS_ERROR_02819", 
            "���q�l���ʔԍ��s���G���[�B");

    /**
     * ���q�l���ʔԍ�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02820 = errorMgr.defineErrorInfo(
            2820,
            "BUSINESS_ERROR_02820", 
            "���q�l���ʔԍ�����G���[�B");

    /**
     * ����IP�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02821 = errorMgr.defineErrorInfo(
            2821,
            "BUSINESS_ERROR_02821", 
            "����IP�G���[�B");

    /**
     * �����Ώۊ��Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02822 = errorMgr.defineErrorInfo(
            2822,
            "BUSINESS_ERROR_02822", 
            "�����Ώۊ��Ԃ�0�ȉ��̒l�ł��B");

    /**
     * �����̒����^����̎�t���\���ǂ������肷��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02824 = errorMgr.defineErrorInfo(
            2824,
            "BUSINESS_ERROR_02824", 
            "���ǌ�\�،���Ж��ɒʒm���󂯂čs�����ǌ�̏o���I���ʒm�I���܂ł̊Ԃ́A���������^�����t��s�Ƃ��܂��B");

    /**
     * �`�[�쐬�󋵃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02825 = errorMgr.defineErrorInfo(
            2825,
            "BUSINESS_ERROR_02825", 
            "�`�[�쐬�󋵂���͂��Ă��������B");

    /**
     * �G���[���R�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02826 = errorMgr.defineErrorInfo(
            2826,
            "BUSINESS_ERROR_02826", 
            "�G���[���R�R�[�h����͂��Ă��������B");

    /**
     * �G���[���R�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02827 = errorMgr.defineErrorInfo(
            2827,
            "BUSINESS_ERROR_02827", 
            "�G���[���R�R�[�h���s���ł��B");

    /**
     * �f�[�^�R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02828 = errorMgr.defineErrorInfo(
            2828,
            "BUSINESS_ERROR_02828", 
            "�f�[�^�R�[�h���s���ł��B");

    /**
     * ���ʃR�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02829 = errorMgr.defineErrorInfo(
            2829,
            "BUSINESS_ERROR_02829", 
            "���ʃR�[�h���s���ł��B");

    /**
     * �A����ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02830 = errorMgr.defineErrorInfo(
            2830,
            "BUSINESS_ERROR_02830", 
            "�A����ʂ��s���ł��B");

    /**
     * �`�[�ʔԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02831 = errorMgr.defineErrorInfo(
            2831,
            "BUSINESS_ERROR_02831", 
            "�`�[�ʔԂ��s���ł��B");

    /**
     * �Ïؔԍ��`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02832 = errorMgr.defineErrorInfo(
            2832,
            "BUSINESS_ERROR_02832", 
            "�Ïؔԍ����s���ł��B");

    /**
     * �����K�{���ڂ�����܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02833 = errorMgr.defineErrorInfo(
            2833,
            "BUSINESS_ERROR_02833", 
            "�����K�{���ڂ�����܂���B");

    /**
     * �`�[���M���`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02834 = errorMgr.defineErrorInfo(
            2834,
            "BUSINESS_ERROR_02834", 
            "�`�[���M�����s���ł��B");

    /**
     * �o�^�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02835 = errorMgr.defineErrorInfo(
            2835,
            "BUSINESS_ERROR_02835", 
            "�o�^�敪�����w��ł��B");

    /**
     * �����t���ŏI����̎������w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02836 = errorMgr.defineErrorInfo(
            2836,
            "BUSINESS_ERROR_02836", 
            "�����t���ŏI����̎������w��s�B");

    /**
     * ���R�[�h�����݂��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02837 = errorMgr.defineErrorInfo(
            2837,
            "BUSINESS_ERROR_02837", 
            "���R�[�h�����݂��܂���B");

    /**
     * �X�V�Ώۂ̃��R�[�h���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02838 = errorMgr.defineErrorInfo(
            2838,
            "BUSINESS_ERROR_02838", 
            "�X�V�Ώۂ̃��R�[�h���s���ł��B");

    /**
     * �����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02839 = errorMgr.defineErrorInfo(
            2839,
            "BUSINESS_ERROR_02839", 
            "�����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B");

    /**
     * ����\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02840 = errorMgr.defineErrorInfo(
            2840,
            "BUSINESS_ERROR_02840", 
            "�㗝���͎҂́A�����F�̋������ϒ����͎���ł��܂���B");

    /**
     * ����\�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02841 = errorMgr.defineErrorInfo(
            2841,
            "BUSINESS_ERROR_02841", 
            "�������ϒ����͎���ł��܂���B");

    /**
     * ����Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02842 = errorMgr.defineErrorInfo(
            2842,
            "BUSINESS_ERROR_02842", 
            "����Ԃ��ς��܂����B���萔�ł����A������x���͂������Ă��������B");

    /**
     * �戵�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02843 = errorMgr.defineErrorInfo(
            2843,
            "BUSINESS_ERROR_02843", 
            "�戵�敪�����w��ł��B");

    /**
     * �戵�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02844 = errorMgr.defineErrorInfo(
            2844,
            "BUSINESS_ERROR_02844", 
            "�戵�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ����J�n���iWEB3�j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02845 = errorMgr.defineErrorInfo(
            2845,
            "BUSINESS_ERROR_02845", 
            "����J�n���iWEB3)�����w��ł��B");

    /**
     * ����I�����iWEB3)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02846 = errorMgr.defineErrorInfo(
            2846,
            "BUSINESS_ERROR_02846", 
            "����I�����iWEB3)�����w��ł��B");

    /**
     * ����J�n���i�C���^�[�l�b�g�j�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02847 = errorMgr.defineErrorInfo(
            2847,
            "BUSINESS_ERROR_02847", 
            "����J�n���i�C���^�[�l�b�g)�����w��ł��B");

    /**
     * ����I�����i�C���^�[�l�b�g)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02848 = errorMgr.defineErrorInfo(
            2848,
            "BUSINESS_ERROR_02848", 
            "����I�����i�C���^�[�l�b�g)�����w��ł��B");

    /**
     * �������iWEB3)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02849 = errorMgr.defineErrorInfo(
            2849,
            "BUSINESS_ERROR_02849", 
            "�������iWEB3)�����w��ł��B");

    /**
     * �������iWEB3)�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02850 = errorMgr.defineErrorInfo(
            2850,
            "BUSINESS_ERROR_02850", 
            "�������iWEB3)�̃T�C�Y���s���ł��B");

    /**
     * �\���P�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02851 = errorMgr.defineErrorInfo(
            2851,
            "BUSINESS_ERROR_02851", 
            "�\���P�ʂ����w��ł��B");

    /**
     * �Œ�z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02852 = errorMgr.defineErrorInfo(
            2852,
            "BUSINESS_ERROR_02852", 
            "�Œ�z�ʂ����w��ł��B");

    /**
     * �ō��z�ʃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02853 = errorMgr.defineErrorInfo(
            2853,
            "BUSINESS_ERROR_02853", 
            "�ō��z�ʂ����w��ł��B");

    /**
     * �ژ_�����{���`�F�b�N�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02854 = errorMgr.defineErrorInfo(
            2854,
            "BUSINESS_ERROR_02854", 
            "�ژ_�����{���`�F�b�N�敪�����w��ł��B");

    /**
     * �ژ_�����{���`�F�b�N�敪�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02855 = errorMgr.defineErrorInfo(
            2855,
            "BUSINESS_ERROR_02855", 
            "�ژ_�����{���`�F�b�N�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ����J�n���iWEB3�j����c�Ɠ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02856 = errorMgr.defineErrorInfo(
            2856,
            "BUSINESS_ERROR_02856", 
            "����J�n���iWEB3�j����c�Ɠ��ł��B");

    /**
     * ����J�n���iSONAR)�Ɖ���J�n���iWEB3)�̊֌W���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02857 = errorMgr.defineErrorInfo(
            2857,
            "BUSINESS_ERROR_02857", 
            "����J�n���iSONAR)�Ɖ���J�n���iWEB3)�̊֌W���s���ł��B");

    /**
     * ����I�����iWEB3)����c�Ɠ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02858 = errorMgr.defineErrorInfo(
            2858,
            "BUSINESS_ERROR_02858", 
            "����I�����iWEB3)����c�Ɠ��ł��B");

    /**
     * ����I�����iSONAR)�Ɖ���I�����iWEB3)�̊֌W���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02859 = errorMgr.defineErrorInfo(
            2859,
            "BUSINESS_ERROR_02859", 
            "����I�����iSONAR)�Ɖ���I�����iWEB3)�̊֌W���s���ł��B");

    /**
     * ����J�n���iWEB3)�Ɖ���I�����iWEB3�j�̊֌W���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02860 = errorMgr.defineErrorInfo(
            2860,
            "BUSINESS_ERROR_02860", 
            "����J�n���iWEB3)�Ɖ���I�����iWEB3�j�̊֌W���s���ł��B");

    /**
     * ����J�n���i�C���^�[�l�b�g�j����c�Ɠ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02861 = errorMgr.defineErrorInfo(
            2861,
            "BUSINESS_ERROR_02861", 
            "����J�n���i�C���^�[�l�b�g�j����c�Ɠ��ł��B");

    /**
     * ����J�n���iWEB3)�Ɖ���J�n���i�C���^�[�l�b�g�j�̊֌W���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02862 = errorMgr.defineErrorInfo(
            2862,
            "BUSINESS_ERROR_02862", 
            "����J�n���iWEB3)�Ɖ���J�n���i�C���^�[�l�b�g�j�̊֌W���s���ł��B");

    /**
     * ����I�����i�C���^�[�l�b�g�j����c�Ɠ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02863 = errorMgr.defineErrorInfo(
            2863,
            "BUSINESS_ERROR_02863", 
            "����I�����i�C���^�[�l�b�g�j����c�Ɠ��ł��B");

    /**
     * ����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02864 = errorMgr.defineErrorInfo(
            2864,
            "BUSINESS_ERROR_02864", 
            "����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B");

    /**
     * ��n�����s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02865 = errorMgr.defineErrorInfo(
            2865,
            "BUSINESS_ERROR_02865", 
            "��n�����s���ł��B");

    /**
     * �\���P�ʂ��s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02866 = errorMgr.defineErrorInfo(
            2866,
            "BUSINESS_ERROR_02866", 
            "�\���P�ʂ��s���ł��B");

    /**
     * �Œ�z�ʂ��s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02867 = errorMgr.defineErrorInfo(
            2867,
            "BUSINESS_ERROR_02867", 
            "�Œ�z�ʂ��s���ł��B");

    /**
     * ����J�n���i�C���^�[�l�b�g)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02868 = errorMgr.defineErrorInfo(
            2868,
            "BUSINESS_ERROR_02868", 
            "����J�n���i�C���^�[�l�b�g)�Ɖ���I�����i�C���^�[�l�b�g�j�̊֌W���s���ł��B");

    /**
     * ���^�C�v�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02869 = errorMgr.defineErrorInfo(
            2869,
            "BUSINESS_ERROR_02869", 
            "���^�C�v�̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ������̏���l����(�s������P��)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02870 = errorMgr.defineErrorInfo(
            2870,
            "BUSINESS_ERROR_02870", 
            "������̏���l����(�s������P��)�G���[�B");

    /**
     * �������̏���l����(�s������P��)�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02871 = errorMgr.defineErrorInfo(
            2871,
            "BUSINESS_ERROR_02871", 
            "�������̏���l����(�s������P��)�G���[�B");

    /**
     * �r�W�l�X�G���[�w���E��c�ύX���ږ����G���[�x�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02872 = errorMgr.defineErrorInfo(
            2872,
            "BUSINESS_ERROR_02872", 
            "�r�W�l�X�G���[�w���E��c�ύX���ږ����G���[�x�B");

    /**
     * �r�W�l�X�G���[�w�ŋ敪�ύX���ږ����G���[�x�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02873 = errorMgr.defineErrorInfo(
            2873,
            "BUSINESS_ERROR_02873", 
            "�r�W�l�X�G���[�w�ŋ敪�ύX���ږ����G���[�x�B");

    /**
     * �r�W�l�X�G���[�w�M�p����ŋ敪�ύX���ږ����G���[�x�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02874 = errorMgr.defineErrorInfo(
            2874,
            "BUSINESS_ERROR_02874", 
            "�r�W�l�X�G���[�w�M�p����ŋ敪�ύX���ږ����G���[�x�B");

    /**
     * �r�W�l�X�G���[�w��������G���[�x�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02875 = errorMgr.defineErrorInfo(
            2875,
            "BUSINESS_ERROR_02875", 
            "�r�W�l�X�G���[�w��������G���[�x�B");

    /**
     * �r�W�l�X�G���[�w����Ǘ������G���[�x�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02876 = errorMgr.defineErrorInfo(
            2876,
            "BUSINESS_ERROR_02876", 
            "�r�W�l�X�G���[�w����Ǘ������G���[�x�B");

    /**
     * �r�W�l�X�G���[�w�d�q��t���ӃG���[�x�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02877 = errorMgr.defineErrorInfo(
            2877,
            "BUSINESS_ERROR_02877", 
            "�r�W�l�X�G���[�w�d�q��t���ӃG���[�x�B");

    /**
     * ���̑����i���t�\�z�i0�␳�����j��0��菬�����l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02878 = errorMgr.defineErrorInfo(
            2878,
            "BUSINESS_ERROR_02878", 
            "���̑����i���t�\�z�i0�␳�����j��0��菬�����l�ł��B");

    /**
     * ���̑����i���t�\�z�i0�␳�����j���w���\�������菬�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02879 = errorMgr.defineErrorInfo(
            2879,
            "BUSINESS_ERROR_02879", 
            "���̑����i���t�\�z�i0�␳�����j���w���\�������菬�����ł��B");

    /**
     * �\�����z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02880 = errorMgr.defineErrorInfo(
            2880,
            "BUSINESS_ERROR_02880", 
            "�\�����z�����w��ł��B");

    /**
     * �\�����z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02881 = errorMgr.defineErrorInfo(
            2881,
            "BUSINESS_ERROR_02881", 
            "�\�����z�������ȊO�̒l�ł��B");

    /**
     * �\�����z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02882 = errorMgr.defineErrorInfo(
            2882,
            "BUSINESS_ERROR_02882", 
            "�\�����z��0�ȉ��̒l�ł��B");

    /**
     * �\�����z�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02883 = errorMgr.defineErrorInfo(
            2883,
            "BUSINESS_ERROR_02883", 
            "�\�����z�̃T�C�Y���s���ł��B");

    /**
     * �@@�l�ڋq�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02884 = errorMgr.defineErrorInfo(
            2884,
            "BUSINESS_ERROR_02884", 
            "�@@�l�ڋq�G���[�B");

    /**
     * �������z���v��������͂��邱�Ƃ��ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02885 = errorMgr.defineErrorInfo(
            2885,
            "BUSINESS_ERROR_02885", 
            "�������z���v��������͂��邱�Ƃ��ł��܂���B");

    /**
     * ���X�ʂ̉���g���v���S���X�̉���g�𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02886 = errorMgr.defineErrorInfo(
            2886,
            "BUSINESS_ERROR_02886", 
            "���X�ʂ̉���g���v���S���X�̉���g�𒴂��Ă��܂��B");

    /**
     * �M�p�������J�݁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02887 = errorMgr.defineErrorInfo(
            2887,
            "BUSINESS_ERROR_02887", 
            "�M�p�����J�݂Ȃ��B");

    /**
     * ���łɉ���g�ɒB���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02888 = errorMgr.defineErrorInfo(
            2888,
            "BUSINESS_ERROR_02888", 
            "���łɉ���g�ɒB���Ă��܂��B");

    /**
     * ����g�𒴉߂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02889 = errorMgr.defineErrorInfo(
            2889,
            "BUSINESS_ERROR_02889", 
            "����g�𒴉߂��Ă��܂��B");

    /**
     * ���f�[�^�d���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02891 = errorMgr.defineErrorInfo(
            2891,
            "BUSINESS_ERROR_02891", 
            "���f�[�^�d���B");

    /**
     * ��{���[���A�h���X�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02892 = errorMgr.defineErrorInfo(
            2892,
            "BUSINESS_ERROR_02892", 
            "��{���[���A�h���X�����w��ł��B");

    /**
     * ���ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02893 = errorMgr.defineErrorInfo(
            2893,
            "BUSINESS_ERROR_02893", 
            "���ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���[���A�h���X�Q�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02894 = errorMgr.defineErrorInfo(
            2894,
            "BUSINESS_ERROR_02894", 
            "���[���A�h���X�Q�����w��ł��B");

    /**
     * ���[���A�h���X�R�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02895 = errorMgr.defineErrorInfo(
            2895,
            "BUSINESS_ERROR_02895", 
            "���[���A�h���X�R�����w��ł��B");

    /**
     * �����ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02896 = errorMgr.defineErrorInfo(
            2896,
            "BUSINESS_ERROR_02896", 
            "�����ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �d�v�A�����[�����M�t���O�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02897 = errorMgr.defineErrorInfo(
            2897,
            "BUSINESS_ERROR_02897", 
            "�d�v�A�����[�����M�t���O�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �ē����[���Q���M�t���O�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02898 = errorMgr.defineErrorInfo(
            2898,
            "BUSINESS_ERROR_02898", 
            "�ē����[���Q���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���[���A�h���X�Q�폜�̏ꍇ�́A���[���A�h���X�Q���w��s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02899 = errorMgr.defineErrorInfo(
            2899,
            "BUSINESS_ERROR_02899", 
            "���[���A�h���X�Q�폜�̏ꍇ�́A���[���A�h���X�Q���w��s�ł��B");

    /**
     * ���[���A�h���X�R�폜�̏ꍇ�́A���[���A�h���X�R���w��s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02900 = errorMgr.defineErrorInfo(
            2900,
            "BUSINESS_ERROR_02900", 
            "���[���A�h���X�R�폜�̏ꍇ�́A���[���A�h���X�R���w��s�ł��B");

    /**
     * ����g�ύX�\���ԊO�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02901 = errorMgr.defineErrorInfo(
            2901,
            "BUSINESS_ERROR_02901", 
            "����g�ύX�\���ԊO�ł��B");

    /**
     * �X�g�b�N���[�������ԍ��̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02902 = errorMgr.defineErrorInfo(
            2902,
            "BUSINESS_ERROR_02902", 
            "�X�g�b�N���[�������ԍ��̃T�C�Y���s���ł��B");

    /**
     * �X�g�b�N���[�������ԍ������p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02903 = errorMgr.defineErrorInfo(
            2903,
            "BUSINESS_ERROR_02903", 
            "�X�g�b�N���[�������ԍ������p�����ȊO�̒l�ł��B");

    /**
     * �\���󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02904 = errorMgr.defineErrorInfo(
            2904,
            "BUSINESS_ERROR_02904", 
            "�\���󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �����From�͐����To���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02905 = errorMgr.defineErrorInfo(
            2905,
            "BUSINESS_ERROR_02905", 
            "�����From�͐����To���傫���ł��B");

    /**
     * ����From�͉���To���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02906 = errorMgr.defineErrorInfo(
            2906,
            "BUSINESS_ERROR_02906", 
            "����From�͉���To���傫���ł��B");

    /**
     * ����From�͕���To���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02907 = errorMgr.defineErrorInfo(
            2907,
            "BUSINESS_ERROR_02907", 
            "����From�͕���To���傫���ł��B");

    /**
     * �S�ۃ��[���\���ڋq���׈ꗗ�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02908 = errorMgr.defineErrorInfo(
            2908,
            "BUSINESS_ERROR_02908", 
            "�S�ۃ��[���\���ڋq���׈ꗗ�����w��ł��B");

    /**
     * �ԍϊz�������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02909 = errorMgr.defineErrorInfo(
            2909,
            "BUSINESS_ERROR_02909", 
            "�ԍϊz�������ȊO�̒l�ł��B");

    /**
     * �ԍϊz�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02910 = errorMgr.defineErrorInfo(
            2910,
            "BUSINESS_ERROR_02910", 
            "�ԍϊz�����w��ł��B");

    /**
     * �ԍϊz�̒l��0�ȉ��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02911 = errorMgr.defineErrorInfo(
            2911,
            "BUSINESS_ERROR_02911", 
            "�ԍϊz�̒l��0�ȉ��ł��B");

    /**
     * �ԍϊz�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02912 = errorMgr.defineErrorInfo(
            2912,
            "BUSINESS_ERROR_02912", 
            "�ԍϊz�̃T�C�Y���s���ł��B");

    /**
     * �ԍϗ\��������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02913 = errorMgr.defineErrorInfo(
            2913,
            "BUSINESS_ERROR_02913", 
            "�ԍϗ\��������w��ł��B");

    /**
     * �،��S�ۃ��[�����������J�݂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02914 = errorMgr.defineErrorInfo(
            2914,
            "BUSINESS_ERROR_02914", 
            "�،��S�ۃ��[�����������J�݂ł��B");

    /**
     * ���ߐU�����͕ԍϗ\������傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02915 = errorMgr.defineErrorInfo(
            2915,
            "BUSINESS_ERROR_02915", 
            "���ߐU�����͕ԍϗ\������傫���ł��B");

    /**
     * �����^�C�v�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02916 = errorMgr.defineErrorInfo(
            2916,
            "BUSINESS_ERROR_02916", 
            "�����^�C�v�����p�����ȊO�̒l�ł��B");

    /**
     * �S�ۖ��������L�[�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02917 = errorMgr.defineErrorInfo(
            2917,
            "BUSINESS_ERROR_02917", 
            "�S�ۖ��������L�[�����w��ł��B");

    /**
     * �o����~�敪�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02918 = errorMgr.defineErrorInfo(
            2918,
            "BUSINESS_ERROR_02918", 
            "�o����~�敪�̃T�C�Y���s���ł��B");

    /**
     * �o����~�敪�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02919 = errorMgr.defineErrorInfo(
            2919,
            "BUSINESS_ERROR_02919", 
            "�o����~�敪�����p�����ȊO�̒l�ł��B");

    /**
     * ����ID�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02920 = errorMgr.defineErrorInfo(
            2920,
            "BUSINESS_ERROR_02920", 
            "����ID�����w��ł��B");

    /**
     * ����ID�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02921 = errorMgr.defineErrorInfo(
            2921,
            "BUSINESS_ERROR_02921", 
            "����ID�̃T�C�Y���s���ł��B");

    /**
     * ����ID�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02922 = errorMgr.defineErrorInfo(
            2922,
            "BUSINESS_ERROR_02922", 
            "����ID�����p�����ȊO�̒l�ł��B");

    /**
     * �ύX������o�^��񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02923 = errorMgr.defineErrorInfo(
            2923,
            "BUSINESS_ERROR_02923", 
            "�ύX������o�^��񂪖��w��ł��B");

    /**
     * �|�ڂ����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02924 = errorMgr.defineErrorInfo(
            2924,
            "BUSINESS_ERROR_02924", 
            "�|�ڂ����p�����ȊO�̒l�ł��B");

    /**
     * �K�i�敪�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02925 = errorMgr.defineErrorInfo(
            2925,
            "BUSINESS_ERROR_02925", 
            "�K�i�敪�����p�����ȊO�̒l�ł��B");

    /**
     * ���R�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02926 = errorMgr.defineErrorInfo(
            2926,
            "BUSINESS_ERROR_02926", 
            "���R�̃T�C�Y���s���ł��B");

    /**
     * ��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02927 = errorMgr.defineErrorInfo(
            2927,
            "BUSINESS_ERROR_02927", 
            "��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B");

    /**
     * ���������I�u�W�F�N�g���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02928 = errorMgr.defineErrorInfo(
            2928,
            "BUSINESS_ERROR_02928", 
            "���������I�u�W�F�N�g���擾�ł��܂���B");

    /**
     * �K�p����from�͗��c�Ɠ���菬�����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02929 = errorMgr.defineErrorInfo(
            2929,
            "BUSINESS_ERROR_02929", 
            "�K�p����from�͗��c�Ɠ���菬�����ł��B");

    /**
     * �K�i�敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02930 = errorMgr.defineErrorInfo(
            2930,
            "BUSINESS_ERROR_02930", 
            "�K�i�敪�����w��ł��B");

    /**
     * �K�i�敪�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02931 = errorMgr.defineErrorInfo(
            2931,
            "BUSINESS_ERROR_02931", 
            "�K�i�敪�̃T�C�Y���s���ł��B");

    /**
     * �K�p���ԋ敪�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02932 = errorMgr.defineErrorInfo(
            2932,
            "BUSINESS_ERROR_02932", 
            "�K�p���ԋ敪�����p�����ȊO�̒l�ł��B");

    /**
     * �K�p���ԋ敪�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02933 = errorMgr.defineErrorInfo(
            2933,
            "BUSINESS_ERROR_02933", 
            "�K�p���ԋ敪�̃T�C�Y���s���ł��B");

    /**
     * �i�@@�l�j�����J�ݕs�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02934 = errorMgr.defineErrorInfo(
            2934,
            "BUSINESS_ERROR_02934", 
            "�i�@@�l�j�����J�ݕs�G���[�B");

    /**
     * �i�a��،��]�����j�����J�ݕs�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02935 = errorMgr.defineErrorInfo(
            2935,
            "BUSINESS_ERROR_02935", 
            "�i�a��،��]�����j�����J�ݕs�G���[�B");

    /**
     * �i�M�p�j�����J�ݕs�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02936 = errorMgr.defineErrorInfo(
            2936,
            "BUSINESS_ERROR_02936", 
            "�i�M�p�j�����J�ݕs�G���[�B");

    /**
     * �i��OP�j�����J�ݕs�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02937 = errorMgr.defineErrorInfo(
            2937,
            "BUSINESS_ERROR_02937", 
            "�i��OP�j�����J�ݕs�G���[�B");

    /**
     * �i�M�p�A��OP�j�����J�ݕs�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02938 = errorMgr.defineErrorInfo(
            2938,
            "BUSINESS_ERROR_02938", 
            "�i�M�p�A��OP�j�����J�ݕs�G���[�B");

    /**
     * ���ʖ���t�G���[�i���O�C�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02939 = errorMgr.defineErrorInfo(
            2939,
            "BUSINESS_ERROR_02939", 
            "���ʂ̌�t������ĂȂ����߁A���O�C���ł��܂���B");

    /**
     * ���ʖ���t�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02940 = errorMgr.defineErrorInfo(
            2940,
            "BUSINESS_ERROR_02940", 
            "���ʂ̌�t������Ă��܂���B");

    /**
     * ���ʋ敪�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02941 = errorMgr.defineErrorInfo(
            2941,
            "BUSINESS_ERROR_02941", 
            "���ʋ敪�����p�����ȊO�̒l�ł��B");

    /**
     * ���ʋ敪�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02942 = errorMgr.defineErrorInfo(
            2942,
            "BUSINESS_ERROR_02942", 
            "���ʋ敪�̃T�C�Y���s���ł��B");

    /**
     * ���ʌ�t�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02943 = errorMgr.defineErrorInfo(
            2943,
            "BUSINESS_ERROR_02943", 
            "���ʌ�t�������w��ł��B");

    /**
     * ���ʃ`�F�b�N�敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02944 = errorMgr.defineErrorInfo(
            2944,
            "BUSINESS_ERROR_02944", 
            "���ʃ`�F�b�N�敪�����w��ł��B");

    /**
     * ���ʃ`�F�b�N�敪�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02945 = errorMgr.defineErrorInfo(
            2945,
            "BUSINESS_ERROR_02945", 
            "���ʃ`�F�b�N�敪�����p�����ȊO�̒l�ł��B");

    /**
     * ���ʃ`�F�b�N�敪�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02946 = errorMgr.defineErrorInfo(
            2946,
            "BUSINESS_ERROR_02946", 
            "���ʃ`�F�b�N�敪�̃T�C�Y���s���ł��B");

    /**
     * ���ʌ�t��From/To�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02947 = errorMgr.defineErrorInfo(
            2947,
            "BUSINESS_ERROR_02947", 
            "���ʌ�t��From/To�������G���[�B");

    /**
     * ���ʋ敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02948 = errorMgr.defineErrorInfo(
            2948,
            "BUSINESS_ERROR_02948", 
            "���ʋ敪�����w��ł��B");

    /**
     * ��W�萔���敪�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02949 = errorMgr.defineErrorInfo(
            2949,
            "BUSINESS_ERROR_02949", 
            "��W�萔���敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �w��̌ڋq�͊��ɓo�^����Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02950 = errorMgr.defineErrorInfo(
            2950,
            "BUSINESS_ERROR_02950", 
            "�w��̌ڋq�͊��ɓo�^����Ă��܂��B");

    /**
     * ����Ԃ������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02951 = errorMgr.defineErrorInfo(
            2951,
            "BUSINESS_ERROR_02951", 
            "����Ԃ������ȊO�̒l�ł��B");

    /**
     * ���ʌ�t�Ǘ��s���擾�ł��Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02952 = errorMgr.defineErrorInfo(
            2952,
            "BUSINESS_ERROR_02952", 
            "���ʌ�t�Ǘ��s���擾�ł��Ȃ��B");

    /**
     * �敨�^�I�v�V�����敪���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02953 = errorMgr.defineErrorInfo(
            2953,
            "BUSINESS_ERROR_02953", 
            "�敨�^�I�v�V�����敪���s���ł��B");

    /**
     * �o���I���敪���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02954 = errorMgr.defineErrorInfo(
            2954,
            "BUSINESS_ERROR_02954", 
            "�o���I���敪���s���ł��B");

    /**
     * �N����l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02955 = errorMgr.defineErrorInfo(
            2955,
            "BUSINESS_ERROR_02955", 
            "�N��N����l�ɒB���Ă��܂���B");

    /**
     * �N�����l�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02956 = errorMgr.defineErrorInfo(
            2956,
            "BUSINESS_ERROR_02956", 
            "�N��N�����l�𒴂��Ă��܂��B");

    /**
     * �x�X���b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02957 = errorMgr.defineErrorInfo(
            2957,
            "BUSINESS_ERROR_02957", 
            "�x�X���b�N�G���[�B");

    /**
     * �Ǘ����b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02958 = errorMgr.defineErrorInfo(
            2958,
            "BUSINESS_ERROR_02958", 
            "�Ǘ����b�N�G���[�B");

    /**
     * �d������ڋq�R�[�h���͎��ʃR�[�h�̐\���݂����݂��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02959 = errorMgr.defineErrorInfo(
            2959,
            "BUSINESS_ERROR_02959", 
            "�d������ڋq�R�[�h���͎��ʃR�[�h�̐\���݂����݂��܂��B");

    /**
     * �w��̌ڋq�R�[�h���͎��ʃR�[�h�͊��Ɍ����J�݌����q�ɓo�^����Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02960 = errorMgr.defineErrorInfo(
            2960,
            "BUSINESS_ERROR_02960", 
            "�w��̌ڋq�R�[�h���͎��ʃR�[�h�͊��Ɍ����J�݌����q�ɓo�^����Ă��܂��B");

    /**
     * ���ʌ�t�����s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02961 = errorMgr.defineErrorInfo(
            2961,
            "BUSINESS_ERROR_02961", 
            "���ʌ�t�����s���ł��B");

    /**
     * ���ɓo�^�ς݂ł������ꍇ�A�o�^�A�b�v���[�h�����s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02962 = errorMgr.defineErrorInfo(
            2962,
            "BUSINESS_ERROR_02962", 
            "���ɓo�^�ς݂ł������ꍇ�A�o�^�A�b�v���[�h�����s�B");

    /**
     * �폜�s�����݂��Ȃ��ꍇ�A�폜�A�b�v���[�h�����s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02963 = errorMgr.defineErrorInfo(
            2963,
            "BUSINESS_ERROR_02963", 
            "�폜�s�����݂��Ȃ��ꍇ�A�폜�A�b�v���[�h�����s�B");

    /**
     * �@@�\�a���ɓ��ӂ���Ă��Ȃ����߁A����ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02964 = errorMgr.defineErrorInfo(
            2964,
            "BUSINESS_ERROR_02964", 
            "�@@�\�a���ɓ��ӂ���Ă��Ȃ����߁A����ł��܂���B");

    /**
     * ������Ԃ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02965 = errorMgr.defineErrorInfo(
            2965,
            "BUSINESS_ERROR_02965", 
            "�����P�ʂ̔����������݂̔�������菬�����l�ł��B");

    /**
     * PTS�����J�݂Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02967 = errorMgr.defineErrorInfo(
            2967,
            "BUSINESS_ERROR_02967", 
            "PTS�����J�݂Ȃ��B");

    /**
     * �w��s��̎���\������z�𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02972 = errorMgr.defineErrorInfo(
            2972,
            "BUSINESS_ERROR_02972", 
            "�w��s��̎���\������z�𒴂��Ă��܂��B");

    /**
     * PTS�s��ł́A���s�w��s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02974 = errorMgr.defineErrorInfo(
            2974,
            "BUSINESS_ERROR_02974", 
            "PTS�s��ł́A���s�w��s�B");

    /**
     * �񋏏Z�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02979 = errorMgr.defineErrorInfo(
            2979,
            "BUSINESS_ERROR_02979", 
            "�񋏏Z�G���[�B");

    /**
     * �K�{���ږ����́B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02980 = errorMgr.defineErrorInfo(
            2980,
            "BUSINESS_ERROR_02980", 
            "�K�{���ږ����́B");

    /**
     * �v�Z�p���͕뉿���z���s���Ȓl�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02981 = errorMgr.defineErrorInfo(
            2981,
            "BUSINESS_ERROR_02981", 
            "�v�Z�p���͕뉿���z���s���Ȓl�B");

    /**
     * �f�[�^�s�����G���[�i�c�������f�[�^�s�����j�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02982 = errorMgr.defineErrorInfo(
            2982,
            "BUSINESS_ERROR_02982", 
            "�f�[�^�s�����G���[�i�c�������f�[�^�s�����j�B");

    /**
     * ������Ԃ��o�����͕s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02983 = errorMgr.defineErrorInfo(
            2983,
            "BUSINESS_ERROR_02983", 
            "������Ԃ��o�����͕s�B");

    /**
     * ���͂������������A�󒍓������ߋ������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02984 = errorMgr.defineErrorInfo(
            2984,
            "BUSINESS_ERROR_02984", 
            "���͂������������A�󒍓������ߋ������B");

    /**
     * �����ɊY�����鋭�����ϒ���������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02985 = errorMgr.defineErrorInfo(
            2985,
            "BUSINESS_ERROR_02985", 
            "�����ɊY�����鋭�����ϒ���������܂���B");

    /**
     * ������Ԃ��o������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02986 = errorMgr.defineErrorInfo(
            2986,
            "BUSINESS_ERROR_02986", 
            "������Ԃ��o������s�B");

    /**
     * ���͂�����芔��������芔���𒴂��Ă���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02987 = errorMgr.defineErrorInfo(
            2987,
            "BUSINESS_ERROR_02987", 
            "���͂�����芔��������芔���𒴂��Ă���B");

    /**
     * ������PTS�s��łȂ����߁A�o�����͕s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02988 = errorMgr.defineErrorInfo(
            2988,
            "BUSINESS_ERROR_02988", 
            "������PTS�s��łȂ����߁A�o�����͕s�B");

    /**
     * ��芔�������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02989 = errorMgr.defineErrorInfo(
            2989,
            "BUSINESS_ERROR_02989", 
            "��芔�������w��ł��B");

    /**
     * ��芔�������l�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02990 = errorMgr.defineErrorInfo(
            2990,
            "BUSINESS_ERROR_02990", 
            "��芔�������l�ȊO�̒l�ł��B");

    /**
     * ��芔����0�ȉ��̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02991 = errorMgr.defineErrorInfo(
            2991,
            "BUSINESS_ERROR_02991", 
            "��芔����0�ȉ��̒l�ł��B");

    /**
     * ��芔���̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02992 = errorMgr.defineErrorInfo(
            2992,
            "BUSINESS_ERROR_02992", 
            "��芔���̃T�C�Y���s���ł��B");

    /**
     * ���P���̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02993 = errorMgr.defineErrorInfo(
            2993,
            "BUSINESS_ERROR_02993", 
            "���P���̃T�C�Y���s���ł��B");

    /**
     * �f�[�^�ϊ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02994 = errorMgr.defineErrorInfo(
            2994,
            "BUSINESS_ERROR_02994", 
            "���t���s���ł��B");

    /**
     * �d�q�������R�[�h�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02995 = errorMgr.defineErrorInfo(
            2995,
            "BUSINESS_ERROR_02995", 
            "�d�q�������R�[�h���s���ł��B");

    /**
     * �d�q�������R�[�h�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02996 = errorMgr.defineErrorInfo(
            2996,
            "BUSINESS_ERROR_02996", 
            "�w�肵���d�q�������R�[�h������������܂��� �i��3�����s���j�B");

    /**
     * ���ʎ�ރR�[�h�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02997 = errorMgr.defineErrorInfo(
            2997,
            "BUSINESS_ERROR_02997", 
            "���ʎ�ރR�[�h���s���ł��B");

    /**
     * ���ʋ敪�Ǘ����R�[�h�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02998 = errorMgr.defineErrorInfo(
            2998,
            "BUSINESS_ERROR_02998", 
            "���ʋ敪�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");

    /**
     * �d�q�������R�[�h�Ǘ����R�[�h�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02999 = errorMgr.defineErrorInfo(
            2999,
            "BUSINESS_ERROR_02999", 
            "�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");

    /**
     * ���ʎ�ފǗ����R�[�h�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03000 = errorMgr.defineErrorInfo(
            3000,
            "BUSINESS_ERROR_03000", 
            "���ʎ�ފǗ��e�[�u���Ƀ��R�[�h�����݂��܂���B");

    /**
     * �f�[�^�ϊ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03002 = errorMgr.defineErrorInfo(
            3002,
            "BUSINESS_ERROR_03002", 
            "���l���s���ł��B");

    /**
     * PTS�s��łȂ��ꍇ�͏o������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03003 = errorMgr.defineErrorInfo(
            3003,
            "BUSINESS_ERROR_03003", 
            "PTS�s��łȂ��ꍇ�͏o������s�B");

    /**
     * �d�q�������R�[�h�������ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03004 = errorMgr.defineErrorInfo(
            3004,
            "BUSINESS_ERROR_03004", 
            "�d�q�������R�[�h�������ł��B");

    /**
     * �L���ȓd�q�������R�[�h���������݂��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03005 = errorMgr.defineErrorInfo(
            3005,
            "BUSINESS_ERROR_03005", 
            "�L���ȓd�q�������R�[�h���������݂��܂��B");

    /**
     * ���ʎ�ވꗗ�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03006 = errorMgr.defineErrorInfo(
            3006,
            "BUSINESS_ERROR_03006", 
            "���ʎ�ވꗗ�����w��ł��B");

    /**
     * ���ʋ敪�Ǘ���񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03007 = errorMgr.defineErrorInfo(
            3007,
            "BUSINESS_ERROR_03007", 
            "���ʋ敪�Ǘ���񂪖��w��ł��B");

    /**
     * �d�q�������R�[�h�Ǘ���񂪖��w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03008 = errorMgr.defineErrorInfo(
            3008,
            "BUSINESS_ERROR_03008", 
            "�d�q�������R�[�h�Ǘ���񂪖��w��ł��B");

    /**
     * �d�q�������R�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03009 = errorMgr.defineErrorInfo(
            3009,
            "BUSINESS_ERROR_03009", 
            "�d�q�������R�[�h�����w��ł��B");

    /**
     * �d�q�������R�[�h�͔��p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03010 = errorMgr.defineErrorInfo(
            3010,
            "BUSINESS_ERROR_03010", 
            "�d�q�������R�[�h�͔��p�����ȊO�̒l�ł��B");

    /**
     * �d�q�������R�[�h�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03011 = errorMgr.defineErrorInfo(
            3011,
            "BUSINESS_ERROR_03011", 
            "�d�q�������R�[�h�̃T�C�Y���s���ł��B");

    /**
     * �L���敪�����ݒ�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03012 = errorMgr.defineErrorInfo(
            3012,
            "BUSINESS_ERROR_03012", 
            "�L���敪�����ݒ�ł��B");

    /**
     * ���ʎ�ރR�[�h�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03013 = errorMgr.defineErrorInfo(
            3013,
            "BUSINESS_ERROR_03013", 
            "���ʎ�ރR�[�h�����w��ł��B");

    /**
     * �L���敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03014 = errorMgr.defineErrorInfo(
            3014,
            "BUSINESS_ERROR_03014", 
            "�L���敪������`�̒l�ł��B");

    /**
     * �����s�̎��ԑсB<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03015 = errorMgr.defineErrorInfo(
            3015,
            "BUSINESS_ERROR_03015", 
            "�����s�̎��ԑсB");

    /**
     * ���i�敪���g���������h�܂��́h�M�p����h�̏ꍇ�A�w����ʂ��w��s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03018 = errorMgr.defineErrorInfo(
            3018,
            "BUSINESS_ERROR_03018", 
            "���i�敪���g���������h�܂��́h�M�p����h�̏ꍇ�A�w����ʂ��w��s�ł��B");

    /**
     * �O���A�g�����擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03019 = errorMgr.defineErrorInfo(
            3019,
            "BUSINESS_ERROR_03019", 
            "�O���A�g�����擾�ł��܂���B");

    /**
     * PTS�����J�݋敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03020 = errorMgr.defineErrorInfo(
            3020,
            "BUSINESS_ERROR_03020", 
            "PTS�����J�݋敪�����w��ł��B");

    /**
     * ����ԍ������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03021 = errorMgr.defineErrorInfo(
            3021,
            "BUSINESS_ERROR_03021", 
            "����ԍ������w��ł��B");

    /**
     * ����񓚂����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03022 = errorMgr.defineErrorInfo(
            3022,
            "BUSINESS_ERROR_03022", 
            "����񓚂����w��ł��B");

    /**
     * �����R�[�h�̗v�f�����O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03023 = errorMgr.defineErrorInfo(
            3023,
            "BUSINESS_ERROR_03023", 
            "�����R�[�h�̗v�f�����O�ł��B");

    /**
     * ���Ɍ����J�ݍς݂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03024 = errorMgr.defineErrorInfo(
            3024,
            "BUSINESS_ERROR_03024", 
            "���Ɍ����J�ݍς݂ł��B");

    /**
     * �����J�݂̐\������t�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03025 = errorMgr.defineErrorInfo(
            3025,
            "BUSINESS_ERROR_03025", 
            "�����J�݂̐\������t�ł��܂���B");

    /**
     * ����񓚂͓��ӂł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03026 = errorMgr.defineErrorInfo(
            3026,
            "BUSINESS_ERROR_03026", 
            "����񓚂͓��ӂł͂���܂���B");

    /**
     * �O���A�g�����擾����͂����Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03027 = errorMgr.defineErrorInfo(
            3027,
            "BUSINESS_ERROR_03027", 
            "�O���A�g�����擾����͂����Ȃ��B");

    /**
     * �V�K�o�^�������O���A�g���g�p�����𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03029 = errorMgr.defineErrorInfo(
            3029,
            "BUSINESS_ERROR_03029", 
            "�V�K�o�^�������O���A�g���g�p�����𒴂��Ă��܂��B");

    /**
     * �X�V�������s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03030 = errorMgr.defineErrorInfo(
            3030,
            "BUSINESS_ERROR_03030", 
            "�X�V�������s���ł��B");

    /**
     * �X�V��񂪕s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03031 = errorMgr.defineErrorInfo(
            3031,
            "BUSINESS_ERROR_03031", 
            "�X�V��񂪕s���ł��B");

    /**
     * ���ʒʔԂ����ݒ�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03032 = errorMgr.defineErrorInfo(
            3032,
            "BUSINESS_ERROR_03032", 
            "���ʒʔԂ����ݒ�ł��B");

    /**
     * ���ʒʔԂ��s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03033 = errorMgr.defineErrorInfo(
            3033,
            "BUSINESS_ERROR_03033", 
            "���ʒʔԂ��s���ł��B");

    /**
     * �E�v�͑S�p�����œ��͂��Ă��������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03034 = errorMgr.defineErrorInfo(
            3034,
            "BUSINESS_ERROR_03034", 
            "�E�v�͑S�p�����œ��͂��Ă��������B");

    /**
     * �E�v��100�����ȓ��Őݒ肵�Ă��������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03035 = errorMgr.defineErrorInfo(
            3035,
            "BUSINESS_ERROR_03035", 
            "�E�v��100�����ȓ��Őݒ肵�Ă��������B");

    /**
     * �L���敪���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03036 = errorMgr.defineErrorInfo(
            3036,
            "BUSINESS_ERROR_03036", 
            "�L���敪���s���ł��B");

    /**
     * �쐬���̏��ʎ�ނ̏��ʒʔԂ͊��ɓo�^����Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03037 = errorMgr.defineErrorInfo(
            3037,
            "BUSINESS_ERROR_03037", 
            "�쐬���̏��ʎ�ނ̏��ʒʔԂ͊��ɓo�^����Ă��܂��B");

    /**
     * �X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03038 = errorMgr.defineErrorInfo(
            3038,
            "BUSINESS_ERROR_03038", 
            "�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B");

    /**
     * �L���ȏ��ʎ�ނ����݂���ׁA�폜�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03039 = errorMgr.defineErrorInfo(
            3039,
            "BUSINESS_ERROR_03039", 
            "�L���ȏ��ʎ�ނ����݂���ׁA�폜�ł��܂���B");

    /**
     * ��t���������݂���ׁA�폜�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03040 = errorMgr.defineErrorInfo(
            3040,
            "BUSINESS_ERROR_03040", 
            "��t���������݂���ׁA�폜�ł��܂���B");

    /**
     * �\�������i���j�͐\�������i���j ���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03041 = errorMgr.defineErrorInfo(
            3041,
            "BUSINESS_ERROR_03041", 
            "�\�������i���j�͐\�������i���j ���傫���ł��B");

    /**
     * �\���敪�͔��p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03042 = errorMgr.defineErrorInfo(
            3042,
            "BUSINESS_ERROR_03042", 
            "�\���敪�͔��p�����ȊO�̒l�ł��B");

    /**
     * �ύX��\���敪�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03045 = errorMgr.defineErrorInfo(
            3045,
            "BUSINESS_ERROR_03045", 
            "�ύX��\���敪�����w��ł��B");

    /**
     * �\���敪�ɕύX������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03046 = errorMgr.defineErrorInfo(
            3046,
            "BUSINESS_ERROR_03046", 
            "�\���敪�ɕύX������܂���B");

    /**
     * �d�q�������R�[�h�Ǘ����R�[�h�o�^�E�X�V�E�폜�`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03049 = errorMgr.defineErrorInfo(
            3049,
            "BUSINESS_ERROR_03049", 
            "�������ʎ�ޒ��ɁA�L���ȏ��ʂ��������݂��܂��B");

    /**
     * �X�e�[�^�X�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03050 = errorMgr.defineErrorInfo(
            3050,
            "BUSINESS_ERROR_03050", 
            "�X�e�[�^�X�̒l���s���ł��B");

    /**
     * �d���ʔԃ`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03051 = errorMgr.defineErrorInfo(
            3051,
            "BUSINESS_ERROR_03051", 
            "�d���ʔԃ`�F�b�N�G���[�i�����l�̍s�����݂���j�B");

    /**
     * �d��ID�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03052 = errorMgr.defineErrorInfo(
            3052,
            "BUSINESS_ERROR_03052", 
            "�d��ID�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");

    /**
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03053 = errorMgr.defineErrorInfo(
            3053,
            "BUSINESS_ERROR_03053", 
            "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s���擾�ł��܂���B");

    /**
     * �ʔԂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03054 = errorMgr.defineErrorInfo(
            3054,
            "BUSINESS_ERROR_03054", 
            "�ʔԂ̃T�C�Y���s���ł��B");

    /**
     * �ʔԂ̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03055 = errorMgr.defineErrorInfo(
            3055,
            "BUSINESS_ERROR_03055", 
            "�ʔԂ����l�ȊO�̒l�ł��B");

    /**
     * �A�b�v���[�h�敪�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03056 = errorMgr.defineErrorInfo(
            3056,
            "BUSINESS_ERROR_03056", 
            "�A�b�v���[�h�敪�����w��ł��B");

    /**
     * ID�`�G�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03057 = errorMgr.defineErrorInfo(
            3057,
            "BUSINESS_ERROR_03057", 
            "ID�����p�p�����ȊO�̒l�ł��B");

    /**
     * �p�X���[�h�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03058 = errorMgr.defineErrorInfo(
            3058,
            "BUSINESS_ERROR_03058", 
            "�p�X���[�h�������͂ł��B");

    /**
     * �ʔԂ�0��菬�����l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03059 = errorMgr.defineErrorInfo(
            3059,
            "BUSINESS_ERROR_03059", 
            "�ʔԂ�0��菬�����l�ł��B");

    /**
     * �ԍό��ʂ̒������ʎw�肪�s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03060 = errorMgr.defineErrorInfo(
            3060,
            "BUSINESS_ERROR_03060", 
            "�ԍό��ʂ̒������ʎw�肪�s���B");

    /**
     * �m�F���T�Z������̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03061 = errorMgr.defineErrorInfo(
            3061,
            "BUSINESS_ERROR_03061", 
            "�m�F���T�Z������̒l���s���ł��B");

    /**
     * �m�F���T�Z���ϑ��v�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03063 = errorMgr.defineErrorInfo(
            3063,
            "BUSINESS_ERROR_03063", 
            "�m�F���T�Z���ϑ��v�̒l���s���ł��B");

    /**
     * �������ʂ��e�����̒������ʂ𒴉߂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03065 = errorMgr.defineErrorInfo(
            3065,
            "BUSINESS_ERROR_03065", 
            "�������ʂ��e�����̒������ʂ𒴉߂��Ă��܂��B");

    /**
     * �\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύςł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03066 = errorMgr.defineErrorInfo(
            3066,
            "BUSINESS_ERROR_03066", 
            "�\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύςł��B");

    /**
     * ���ʖ����� �������O�A�E�g�Ώۖ��������̃`�F�b�N�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03067 = errorMgr.defineErrorInfo(
            3067,
            "BUSINESS_ERROR_03067", 
            "���ʖ����� �������O�A�E�g�Ώۖ��������������͂ł��B");

    /**
     * �K�p�J�n���i���j���K�p�J�n���i���j��薢�����t�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03068 = errorMgr.defineErrorInfo(
            3068,
            "BUSINESS_ERROR_03068", 
            "�K�p�J�n���i���j���K�p�J�n���i���j��薢�����t�ł��B");

    /**
     * PTS�s��ŃX�g�b�N�I�v�V�����������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03069 = errorMgr.defineErrorInfo(
            3069,
            "BUSINESS_ERROR_03069", 
            "PTS�s��ŃX�g�b�N�I�v�V�����������s�B");

    /**
     * MQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03070 = errorMgr.defineErrorInfo(
            3070,
            "BUSINESS_ERROR_03070", 
            "MQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B");

    /**
     * �V�F�����̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03071 = errorMgr.defineErrorInfo(
            3071,
            "BUSINESS_ERROR_03071", 
            "�V�F�����̂����w��ł��B");

    /**
     * �g���K�[���̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03072 = errorMgr.defineErrorInfo(
            3072,
            "BUSINESS_ERROR_03072", 
            "�g���K�[���̂����w��ł��B");

    /**
     * �Ĕ��s�\�t���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03073 = errorMgr.defineErrorInfo(
            3073,
            "BUSINESS_ERROR_03073", 
            "�Ĕ��s�\�t���O�����w��ł��B");

    /**
     * ���[�U�[�f�[�^�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03074 = errorMgr.defineErrorInfo(
            3074,
            "BUSINESS_ERROR_03074", 
            "���[�U�[�f�[�^�����w��ł��B");

    /**
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j�̃��R�[�h���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03075 = errorMgr.defineErrorInfo(
            3075,
            "BUSINESS_ERROR_03075", 
            "�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j�̃��R�[�h���擾�ł��܂���B");

    /**
     * ���N�G�X�g�f�[�^�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03076 = errorMgr.defineErrorInfo(
            3076,
            "BUSINESS_ERROR_03076", 
            "���N�G�X�g�f�[�^�����w��ł��B");

    /**
     * ���N�G�X�g�f�[�^�̗v�f�����O�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03077 = errorMgr.defineErrorInfo(
            3077,
            "BUSINESS_ERROR_03077", 
            "���N�G�X�g�f�[�^�̗v�f�����O�ł��B");

    /**
     * �V�X�e����Q�t���O�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03078 = errorMgr.defineErrorInfo(
            3078,
            "BUSINESS_ERROR_03078", 
            "�V�X�e����Q�t���O�����p�����ȊO�̒l�ł��B");

    /**
     * �V�X�e����Q�t���O�̃T�C�Y���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03079 = errorMgr.defineErrorInfo(
            3079,
            "BUSINESS_ERROR_03079", 
            "�V�X�e����Q�t���O�̃T�C�Y���s���ł��B");

    /**
     * �V�X�e����Q�t���O�����w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03080 = errorMgr.defineErrorInfo(
            3080,
            "BUSINESS_ERROR_03080", 
            "�V�X�e����Q�t���O�����w��ł��B");

    /**
     * �폜�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03081 = errorMgr.defineErrorInfo(
            3081,
            "BUSINESS_ERROR_03081", 
            "�폜�s�B");

    /**
     * ���ʎc���s���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03082 = errorMgr.defineErrorInfo(
            3082,
            "BUSINESS_ERROR_03082", 
            "���ʎc���s���G���[�B");

    /**
     * ��ʃR�[�h�̌�����3�����z���Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03083 = errorMgr.defineErrorInfo(
            3083,
            "BUSINESS_ERROR_03083", 
            "��ʃR�[�h�̌�����3�����z���Ă��܂��B");

    /**
     * ��ʃR�[�h�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03084 = errorMgr.defineErrorInfo(
            3084,
            "BUSINESS_ERROR_03084", 
            "��ʃR�[�h�����p�����ȊO�̒l�ł��B");

    /**
     * ��������null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03085 = errorMgr.defineErrorInfo(
            3085,
            "BUSINESS_ERROR_03085", 
            "�����������w��ł��B");

    /**
     * �������̌�����10���𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03086 = errorMgr.defineErrorInfo(
            3086,
            "BUSINESS_ERROR_03086", 
            "�������̌�����10���𒴂��Ă��܂��B");

    /**
     * �����������p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03087 = errorMgr.defineErrorInfo(
            3087,
            "BUSINESS_ERROR_03087", 
            "�����������p�����ȊO�̒l�ł��B");

    /**
     * ���z��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03088 = errorMgr.defineErrorInfo(
            3088,
            "BUSINESS_ERROR_03088", 
            "���z�����w��ł��B");

    /**
     * ���z�̌�����12���𒴂��Ă��܂��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03089 = errorMgr.defineErrorInfo(
            3089,
            "BUSINESS_ERROR_03089", 
            "���z�̌�����12���𒴂��Ă��܂��B");

    /**
     * ���z�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03090 = errorMgr.defineErrorInfo(
            3090,
            "BUSINESS_ERROR_03090", 
            "���z�����p�����ȊO�̒l�ł��B");

    /**
     * �⏕������null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03091 = errorMgr.defineErrorInfo(
            3091,
            "BUSINESS_ERROR_03091", 
            "�⏕���������w��ł��B");

    /**
     * �ژ_�����{���`�F�b�N���ʂ��{�����ςł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03092 = errorMgr.defineErrorInfo(
            3092,
            "BUSINESS_ERROR_03092", 
            "�ژ_�����{���`�F�b�N���ʂ��{�����ςł��B");

    /**
     * ���O�C���������擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03094 = errorMgr.defineErrorInfo(
            3094,
            "BUSINESS_ERROR_03094", 
            "���O�C���������擾�ł��܂���B");

    /**
     * ����No�d���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03095 = errorMgr.defineErrorInfo(
            3095,
            "BUSINESS_ERROR_03095", 
            "����No�d���G���[�B");

    /**
     * ����f�[�^�Ȃ��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03096 = errorMgr.defineErrorInfo(
            3096,
            "BUSINESS_ERROR_03096", 
            "����f�[�^�Ȃ��G���[�B");

    /**
     * ��萔�ʂƒ������ʂ͓����ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03098 = errorMgr.defineErrorInfo(
            3098,
            "BUSINESS_ERROR_03098", 
            "��萔�ʂƒ������ʂ͓����ł��B");

    /**
     * �莞��z���t�����������o�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03099 = errorMgr.defineErrorInfo(
            3099,
            "BUSINESS_ERROR_03099", 
            "�莞��z���t�����������o�^�G���[�B");

    /**
     * ����\���t���O��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03100 = errorMgr.defineErrorInfo(
            3100,
            "BUSINESS_ERROR_03100", 
            "����\���t���O�����w��ł��B");

    /**
     * ����\���t���O�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03101 = errorMgr.defineErrorInfo(
            3101,
            "BUSINESS_ERROR_03101", 
            "����\���t���O�̒l���s���ł��B");

    /**
     * �ύX�敪���w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03102 = errorMgr.defineErrorInfo(
            3102,
            "BUSINESS_ERROR_03102", 
            "�ύX�敪���w��G���[�B");

    /**
     * �K�p�J�n�N�����w��G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03103 = errorMgr.defineErrorInfo(
            3103,
            "BUSINESS_ERROR_03103", 
            "�K�p�J�n�N�����w��G���[�B");

    /**
     * �����������s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03104 = errorMgr.defineErrorInfo(
            3104,
            "BUSINESS_ERROR_03104", 
            "�����������s�B");

    /**
     * PTYPE��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03105 = errorMgr.defineErrorInfo(
            3105,
            "BUSINESS_ERROR_03105", 
            "PTYPE�����w��ł��B");

    /**
     * ��������̏ꍇ�A���助�U�`���Ɖ�����󂯋敪�͕K�{���͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03106 = errorMgr.defineErrorInfo(
            3106,
            "BUSINESS_ERROR_03106", 
            "��������̏ꍇ�A���助�U�`���Ɖ�����󂯋敪�͕K�{���͂ł��B");

    /**
     * ��������łȂ��ꍇ�A���助�U�`���Ɖ�����󂯋敪�͓��͕s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03107 = errorMgr.defineErrorInfo(
            3107,
            "BUSINESS_ERROR_03107", 
            "��������łȂ��ꍇ�A���助�U�`���Ɖ�����󂯋敪�͓��͕s�ł��B");

    /**
     * �M�p�����J�݂Ȃ��A���萔���敪�͐M�p�ڋq�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03108 = errorMgr.defineErrorInfo(
            3108,
            "BUSINESS_ERROR_03108", 
            "�M�p�����J�݂Ȃ��A���萔���敪�͐M�p�ڋq�ł��B");

    /**
     * �����������������t�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03109 = errorMgr.defineErrorInfo(
            3109,
            "BUSINESS_ERROR_03109", 
            "�����������������t�ł͂���܂���B");

    /**
     * �������K�p�J�n�̐\�����������邽�߁A�V�K�����̒ǉ��\���͂ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03110 = errorMgr.defineErrorInfo(
            3110,
            "BUSINESS_ERROR_03110", 
            "�������K�p�J�n�̐\�����������邽�߁A�V�K�����̒ǉ��\���͂ł��܂���B");

    /**
     * ���p�ґ����̕�������1byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03111 = errorMgr.defineErrorInfo(
            3111,
            "BUSINESS_ERROR_03111", 
            "���p�ґ����̕�������1byte���傫���ł��B");

    /**
     * ���ϕ��@@�̕�������1byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03112 = errorMgr.defineErrorInfo(
            3112,
            "BUSINESS_ERROR_03112", 
            "���ϕ��@@�̕�������1byte���傫���ł��B");

    /**
     * �d�q��t�������̕�������8byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03113 = errorMgr.defineErrorInfo(
            3113,
            "BUSINESS_ERROR_03113", 
            "�d�q��t�������̕�������8byte���傫���ł��B");

    /**
     * ����������m�F���̕�������8byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03114 = errorMgr.defineErrorInfo(
            3114,
            "BUSINESS_ERROR_03114", 
            "����������m�F���̕�������8byte���傫���ł��B");

    /**
     * ������ԍ��̕�������10byte���傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03115 = errorMgr.defineErrorInfo(
            3115,
            "BUSINESS_ERROR_03115", 
            "������ԍ��̕�������10byte���傫���ł��B");

    /**
     * ���O�C������ID��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03116 = errorMgr.defineErrorInfo(
            3116,
            "BUSINESS_ERROR_03116", 
            "���O�C������ID�����w��ł��B");

    /**
     * ���O�C������ID�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03117 = errorMgr.defineErrorInfo(
            3117,
            "BUSINESS_ERROR_03117", 
            "���O�C������ID�����p�����ȊO�̒l�ł��B");

    /**
     * ����(��)��null�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03118 = errorMgr.defineErrorInfo(
            3118,
            "BUSINESS_ERROR_03118", 
            "����(��)��null�ł��B");

    /**
     * ����(��)��null�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03119 = errorMgr.defineErrorInfo(
            3119,
            "BUSINESS_ERROR_03119", 
            "����(��)��null�ł��B");

    /**
     * �w��͈͂͂R���Ԉȓ��œ��͂��Ă��������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03120 = errorMgr.defineErrorInfo(
            3120,
            "BUSINESS_ERROR_03120", 
            "�w��͈͂͂R���Ԉȓ��œ��͂��Ă��������B");

    /**
     * IP�A�h���X�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03121 = errorMgr.defineErrorInfo(
            3121,
            "BUSINESS_ERROR_03121", 
            "IP�A�h���X�̒l���s���ł��B");

    /**
     * IP�A�h���X��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03122 = errorMgr.defineErrorInfo(
            3122,
            "BUSINESS_ERROR_03122", 
            "IP�A�h���X�����w��ł��B");

    /**
     * �X�e�[�^�X�����p�����ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03123 = errorMgr.defineErrorInfo(
            3123,
            "BUSINESS_ERROR_03123", 
            "�X�e�[�^�X�����p�����ȊO�̒l�ł��B");

    /**
     * �K�p�J�n���������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03124 = errorMgr.defineErrorInfo(
            3124,
            "BUSINESS_ERROR_03124", 
            "�K�p�J�n���������w��ł��B");

    /**
     * �K�p�I�����������w��ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03125 = errorMgr.defineErrorInfo(
            3125,
            "BUSINESS_ERROR_03125", 
            "�K�p�I�����������w��ł��B");

    /**
     * �K�p�I�������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03126 = errorMgr.defineErrorInfo(
            3126,
            "BUSINESS_ERROR_03126", 
            "�K�p�I�������G���[(�K�p�I������ ��= ���ݓ���)�B");

    /**
     * �K�p�J�n�����͓K�p�I���������傫���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03127 = errorMgr.defineErrorInfo(
            3127,
            "BUSINESS_ERROR_03127", 
            "�K�p�J�n�����͓K�p�I���������傫���ł��B");

    /**
     * �w��͈͂͂P���Ԉȓ��œ��͂��Ă��������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03128 = errorMgr.defineErrorInfo(
            3128,
            "BUSINESS_ERROR_03128", 
            "�w��͈͂͂P���Ԉȓ��œ��͂��Ă��������B");

    /**
     * �����N�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03129 = errorMgr.defineErrorInfo(
            3129,
            "BUSINESS_ERROR_03129", 
            "�����N�������͂ł��B");

    /**
     * �����N�͔��p�����œ��͂��Ă��������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03130 = errorMgr.defineErrorInfo(
            3130,
            "BUSINESS_ERROR_03130", 
            "�����N�͔��p�����œ��͂��Ă��������B");

    /**
     * �����N�͏��30�ʂ܂ł̕\�������ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03131 = errorMgr.defineErrorInfo(
            3131,
            "BUSINESS_ERROR_03131", 
            "�����N�͏��30�ʂ܂ł̕\�������ł��܂���B");

    /**
     * ���͓��e�ɕύX������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03132 = errorMgr.defineErrorInfo(
            3132,
            "BUSINESS_ERROR_03132", 
            "���͓��e�ɕύX������܂���B");

    /**
     * CFD�����J�ݏ������ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03133 = errorMgr.defineErrorInfo(
            3133,
            "BUSINESS_ERROR_03133", 
            "CFD�����J�ݏ������ł��B");

    /**
     * ��菈�����̏ꍇ�A�o��������s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03134 = errorMgr.defineErrorInfo(
            3134,
            "BUSINESS_ERROR_03134", 
            "��菈�����̏ꍇ�A�o��������s�ł��B");

    /**
     * �O���ڑ��V�X�e���R�[�h�̒l��GFT��TFX�ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03135 = errorMgr.defineErrorInfo(
            3135,
            "BUSINESS_ERROR_03135", 
            "�O���ڑ��V�X�e���R�[�h�̒l��GFT��TFX�ȊO�̒l�ł��B");

    /**
     * �������R��null�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03136 = errorMgr.defineErrorInfo(
            3136,
            "BUSINESS_ERROR_03136", 
            "�������R��null�ł��B");

    /**
     * ���֋�/���ʗ��֋��A�s�����i�����j�܂��͎w��Ȃ����I������Ă���ꍇ�A������0�ł͂Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03138 = errorMgr.defineErrorInfo(
            3138,
            "BUSINESS_ERROR_03138", 
            "���֋�/���ʗ��֋��A�s�����i�����j�܂��͎w��Ȃ����I������Ă���ꍇ�A������0�ł͂Ȃ��B");

    /**
     * �ڋq������null�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03140 = errorMgr.defineErrorInfo(
            3140,
            "BUSINESS_ERROR_03140", 
            "�ڋq������null�ł��B");

    /**
     * �m�F�t���O�`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03141 = errorMgr.defineErrorInfo(
            3141,
            "BUSINESS_ERROR_03141", 
            "�m�F�t���O�����`�F�b�N�ł��B");

    /**
     * �����J�݌����q�f�[�^�폜�s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03142 = errorMgr.defineErrorInfo(
            3142,
            "BUSINESS_ERROR_03142", 
            "�����J�݌����q�f�[�^�͍폜�ł��܂���B");

    /**
     * ����ۏ؋���L�����߃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03143 = errorMgr.defineErrorInfo(
            3143,
            "BUSINESS_ERROR_03143", 
            "����ۏ؋���L�����߃G���[�B");

    /**
     * ���ӏ���ʂ�����`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03147 = errorMgr.defineErrorInfo(
            3147,
            "BUSINESS_ERROR_03147", 
            "���ӏ���ʂ�����`�̒l�B");

    /**
     * ���ӏ���ʁ^���ӏ��敪�w�肪�s�����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03148 = errorMgr.defineErrorInfo(
            3148,
            "BUSINESS_ERROR_03148", 
            "���ӏ���ʁ^���ӏ��敪�w�肪�s�����B");

    /**
     * ���ӏ��敪�R�[�h������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03149 = errorMgr.defineErrorInfo(
            3149,
            "BUSINESS_ERROR_03149", 
            "���ӏ��敪�R�[�h������`�̒l�B");

    /**
     * �L�����G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03150 = errorMgr.defineErrorInfo(
            3150,
            "BUSINESS_ERROR_03150", 
            "�L�����G���[�B");

    /**
     * ��񔭐�����From�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03151 = errorMgr.defineErrorInfo(
            3151,
            "BUSINESS_ERROR_03151", 
            "��񔭐�����From�G���[�B");

    /**
     * ��񔭐�����To�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03152 = errorMgr.defineErrorInfo(
            3152,
            "BUSINESS_ERROR_03152", 
            "��񔭐�����To�G���[�B");

    /**
     * ������̎w�肪����������܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03153 = errorMgr.defineErrorInfo(
            3153,
            "BUSINESS_ERROR_03153", 
            "������̎w�肪����������܂���B");

    /**
     * �������t��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03154 = errorMgr.defineErrorInfo(
            3154,
            "BUSINESS_ERROR_03154", 
            "�������t��null�B");

    /**
     * �������q�敪��null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03155 = errorMgr.defineErrorInfo(
            3155,
            "BUSINESS_ERROR_03155", 
            "�������q�敪��null�B");

    /**
     * �������q�敪������`�̒l�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03156 = errorMgr.defineErrorInfo(
            3156,
            "BUSINESS_ERROR_03156", 
            "�������q�敪������`�̒l�B");

    /**
     * �{���̏؋����s���͂܂��m�F���Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03157 = errorMgr.defineErrorInfo(
            3157,
            "BUSINESS_ERROR_03157", 
            "�{���̏؋����s���͂܂��m�F���Ă��܂���B");

    /**
     * �����ɊY������؋����s���󋵏�񂪂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03158 = errorMgr.defineErrorInfo(
            3158,
            "BUSINESS_ERROR_03158", 
            "�����ɊY������؋����s���󋵏�񂪂���܂���B");

    /**
     * �t���敪��null�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03160 = errorMgr.defineErrorInfo(
            3160,
            "BUSINESS_ERROR_03160", 
            "�t���敪��null�ł��B");

    /**
     * �O���ڑ��V�X�e���R�[�h�̒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03161 = errorMgr.defineErrorInfo(
            3161,
            "BUSINESS_ERROR_03161", 
            "�O���ڑ��V�X�e���R�[�h�̒l���s���ł��B");

    /**
     * �w�肵���R�[�X�̉\�z���擾�ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03162 = errorMgr.defineErrorInfo(
            3162,
            "BUSINESS_ERROR_03162", 
            "�w�肵���R�[�X�̉\�z���擾�ł��܂���B");

    /**
     * �^�p�R�[�h��5���̔��p�����ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03163 = errorMgr.defineErrorInfo(
            3163,
            "BUSINESS_ERROR_03163", 
            "�^�p�R�[�h��5���̔��p�����ł͂���܂���B");

    /**
     * �^�p�R�[�h�̍�2byte������.�O�������^�p�R�[�h�̔ԋ敪�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03164 = errorMgr.defineErrorInfo(
            3164,
            "BUSINESS_ERROR_03164", 
            "�^�p�R�[�h�̍�2byte������.�O�������^�p�R�[�h�̔ԋ敪�ł͂���܂���B");

    /**
     * ���[���A�h���X�o�^ID�ƃ��[���A�h���X�̃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03165 = errorMgr.defineErrorInfo(
            3165,
            "BUSINESS_ERROR_03165", 
            "���[���A�h���X�o�^ID�ƃ��[���A�h���X�͓����ɓ��͂����B");

    /**
     * ���������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03167 = errorMgr.defineErrorInfo(
            3167,
            "BUSINESS_ERROR_03167", 
            "���������͂ł��B");

    /**
     * ���͂������[���A�h���X���g�у��[���A�h���X�ɂł��邩�`�F�b�N����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03168 = errorMgr.defineErrorInfo(
            3168,
            "BUSINESS_ERROR_03168", 
            "���͂���郁�[���A�h���X�͌g�у��[���A�h���X�ł���B");

    /**
     * �X�V���ڂ������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03169 = errorMgr.defineErrorInfo(
            3169,
            "BUSINESS_ERROR_03169", 
            "�X�V���ڂ������͂ł��B");

    /**
     * �X�V���ڂ����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03170 = errorMgr.defineErrorInfo(
            3170,
            "BUSINESS_ERROR_03170", 
            "�X�V���ڂ����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �X�V���Ԃ������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03171 = errorMgr.defineErrorInfo(
            3171,
            "BUSINESS_ERROR_03171", 
            "�X�V���Ԃ������͂ł��B");

    /**
     * �X�V���Ԃ����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03172 = errorMgr.defineErrorInfo(
            3172,
            "BUSINESS_ERROR_03172", 
            "�X�V���Ԃ����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �폜�t���O�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03173 = errorMgr.defineErrorInfo(
            3173,
            "BUSINESS_ERROR_03173", 
            "�폜�t���O�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ����t���O�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03174 = errorMgr.defineErrorInfo(
            3174,
            "BUSINESS_ERROR_03174", 
            "����t���O�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ��̃t���O�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03175 = errorMgr.defineErrorInfo(
            3175,
            "BUSINESS_ERROR_03175", 
            "��̃t���O�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �O���l�t���O���݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03176 = errorMgr.defineErrorInfo(
            3176,
            "BUSINESS_ERROR_03176", 
            "�O���l�t���O���݂��Ȃ��R�[�h�l�ł��B");

    /**
     * �X�V���Ԃ̓��͒l���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03177 = errorMgr.defineErrorInfo(
            3177,
            "BUSINESS_ERROR_03177", 
            "�X�V���Ԃ̓��͒l���s���ł��B");

    /**
     * �����J�݌����q���폜�ςł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03178 = errorMgr.defineErrorInfo(
            3178,
            "BUSINESS_ERROR_03178", 
            "�����J�݌����q���폜�ςł��B");

    /**
     * �����J�݃f�[�^�ڊǏ������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03179 = errorMgr.defineErrorInfo(
            3179,
            "BUSINESS_ERROR_03179", 
            "�����J�݃f�[�^�ڊǏ������B");

    /**
     * �ڋq�敪�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03180 = errorMgr.defineErrorInfo(
            3180,
            "BUSINESS_ERROR_03180", 
            "�ڋq�敪�������͂ł��B");

    /**
     * �����J�ݏ󋵂����J�݈ȊO�̏ꍇ�A�폜�t���O�ؑ֕s�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03181 = errorMgr.defineErrorInfo(
            3181,
            "BUSINESS_ERROR_03181", 
            "�����J�ݏ󋵂����J�݈ȊO�̏ꍇ�A�폜�t���O�ؑ֕s�B");

    /**
     * �Ǘ��ҋ敪���݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03182 = errorMgr.defineErrorInfo(
            3182,
            "BUSINESS_ERROR_03182", 
            "�Ǘ��ҋ敪���݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���[���A�h���X�o�^ID ���̓��[���A�h���X�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03183 = errorMgr.defineErrorInfo(
            3183,
            "BUSINESS_ERROR_03183", 
            "���[���A�h���X�o�^ID ���̓��[���A�h���X�������͂ł��B");

    /**
     * �����敪���s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03184 = errorMgr.defineErrorInfo(
            3184,
            "BUSINESS_ERROR_03184", 
            "�����敪���s���ł��B");

    /**
     * FX�Ïؔԍ��Q��FX�Ïؔԍ�����v�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03185 = errorMgr.defineErrorInfo(
            3185,
            "BUSINESS_ERROR_03185", 
            "FX�Ïؔԍ��Q��FX�Ïؔԍ�����v�ł��B");

    /**
     * �����ғo�^�敪�����݂��Ȃ��R�[�h�l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03186 = errorMgr.defineErrorInfo(
            3186,
            "BUSINESS_ERROR_03186", 
            "�����ғo�^�敪�����݂��Ȃ��R�[�h�l�ł��B");

    /**
     * ���͂��ꂽ���[���A�h���X�͌g�у��[���A�h���X�ł͂���܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03189 = errorMgr.defineErrorInfo(
            3189,
            "BUSINESS_ERROR_03189", 
            "���͂��ꂽ���[���A�h���X�͌g�у��[���A�h���X�ł͂���܂���B");

    /**
     * ���[���A�h���X�ԍ��������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03190 = errorMgr.defineErrorInfo(
            3190,
            "BUSINESS_ERROR_03190", 
            "���[���A�h���X�ԍ��������͂ł��B");

    /**
     * ���[���A�h���X�ԍ��������ȊO�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03191 = errorMgr.defineErrorInfo(
            3191,
            "BUSINESS_ERROR_03191", 
            "���[���A�h���X�ԍ��������ȊO�̒l�ł��B");

    /**
     * ���[���A�h���X�ԍ��̓��͂��s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03192 = errorMgr.defineErrorInfo(
            3192,
            "BUSINESS_ERROR_03192", 
            "���[���A�h���X�ԍ��̓��͂��s���ł��B");

    /**
     * ���[���A�h���X�敪�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03193 = errorMgr.defineErrorInfo(
            3193,
            "BUSINESS_ERROR_03193", 
            "���[���A�h���X�敪�������͂ł��B");

    /**
     * ���[���A�h���X�ԍ������[���A�h���X���ɑ��݂��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03194 = errorMgr.defineErrorInfo(
            3194,
            "BUSINESS_ERROR_03194", 
            "���[���A�h���X�ԍ������[���A�h���X���ɑ��݂��܂���B");

    /**
     * ���[����ʔԍ��������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03195 = errorMgr.defineErrorInfo(
            3195,
            "BUSINESS_ERROR_03195", 
            "���[����ʔԍ��������͂ł��B");

    /**
     * ���[����ʔԍ��̓��͂��s���ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03196 = errorMgr.defineErrorInfo(
            3196,
            "BUSINESS_ERROR_03196", 
            "���[����ʔԍ��̓��͂��s���ł��B");

    /**
     * �I���������[���A�h���X�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03197 = errorMgr.defineErrorInfo(
            3197,
            "BUSINESS_ERROR_03197", 
            "�I���������[���A�h���X�������͂ł��B");

    /**
     * �d�q��t���[����PC���[���A�h���X�݂̂Ɏw��\�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03198 = errorMgr.defineErrorInfo(
            3198,
            "BUSINESS_ERROR_03198", 
            "�d�q��t���[����PC���[���A�h���X�݂̂Ɏw��\�ł��B");

    /**
     * ���s�����̏ꍇ�A�����t�����͂��戵���ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03199 = errorMgr.defineErrorInfo(
            3199,
            "BUSINESS_ERROR_03199", 
            "���s�����̏ꍇ�A�����t�����͂��戵���ł��܂���B");

    /**
     * W�w�l�����̃X�g�b�v�����͐��s�w��s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03200 = errorMgr.defineErrorInfo(
            3200,
            "BUSINESS_ERROR_03200", 
            "W�w�l�����̃X�g�b�v�����͐��s�w��s�ł��B");

    /**
     * �w�l���琬�s�ցA���邢�͐��s����w�l�ւ̒����͕s�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03201 = errorMgr.defineErrorInfo(
            3201,
            "BUSINESS_ERROR_03201", 
            "�w�l���琬�s�ցA���邢�͐��s����w�l�ւ̒����͕s�ł��B");

    /**
     * �Г��������ڂ�null�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03202 = errorMgr.defineErrorInfo(
            3202,
            "BUSINESS_ERROR_03202", 
            "�Г��������ڂ�null�ł��B");

    /**
     * �s�ꖢ�������������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03203 = errorMgr.defineErrorInfo(
            3203,
            "BUSINESS_ERROR_03203", 
            "�����ɊY������s�ꖢ���������͑��݂��܂���B");

    /**
     * CSV�t�H�[�}�b�g���o�^�G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03204 = errorMgr.defineErrorInfo(
            3204,
            "BUSINESS_ERROR_03204", 
            "�t�H�[�}�b�g���o�^����Ă��܂���B");

    /**
     * �Г��������ڃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03205 = errorMgr.defineErrorInfo(
            3205,
            "BUSINESS_ERROR_03205", 
            "�Г��������ڂ��s���ł��B");

    /**
     * �Y������Г����ڂ̒��������G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03206 = errorMgr.defineErrorInfo(
            3206,
            "BUSINESS_ERROR_03206", 
            "�Y������Г����ڂ̒����͂���܂���B");

    /**
     * ���z�T�[�o���G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03207 = errorMgr.defineErrorInfo(
            3207,
            "BUSINESS_ERROR_03207", 
            "���z�T�[�o��񂪂Ȃ��ł��B");

    /**
     * ������ʃG���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03208 = errorMgr.defineErrorInfo(
            3208,
            "BUSINESS_ERROR_03208", 
            "������ʂ��s���ł��B");

    /**
     * ����敪�̕����s�̃��R�[�h���I������܂����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03209 = errorMgr.defineErrorInfo(
            3209,
            "BUSINESS_ERROR_03209", 
            "����敪�̕����s�̃��R�[�h���I������܂����B");

    /**
     * �d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03210 = errorMgr.defineErrorInfo(
            3210,
            "BUSINESS_ERROR_03210", 
            "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");

    /**
     * ����񍐏���t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03211 = errorMgr.defineErrorInfo(
            3211,
            "BUSINESS_ERROR_03211", 
            "����񍐏���t�敪������`�̒l�ł��B");

    /**
     * ����c���񍐏���t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03212 = errorMgr.defineErrorInfo(
            3212,
            "BUSINESS_ERROR_03212", 
            "����c���񍐏���t�敪������`�̒l�ł��B");

    /**
     * �^�p�񍐏���t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03213 = errorMgr.defineErrorInfo(
            3213,
            "BUSINESS_ERROR_03213", 
            "�^�p�񍐏���t�敪������`�̒l�ł��B");

    /**
     * �񊼁E�K��W�񍐏���t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03214 = errorMgr.defineErrorInfo(
            3214,
            "BUSINESS_ERROR_03214", 
            "�񊼁E�K��W�񍐏���t�敪������`�̒l�ł��B");

    /**
     * ���ʂP��t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03215 = errorMgr.defineErrorInfo(
            3215,
            "BUSINESS_ERROR_03215", 
            "���ʂP��t�敪������`�̒l�ł��B");

    /**
     * ���ʂQ��t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03216 = errorMgr.defineErrorInfo(
            3216,
            "BUSINESS_ERROR_03216", 
            "���ʂQ��t�敪������`�̒l�ł��B");

    /**
     * ���ʂR��t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03217 = errorMgr.defineErrorInfo(
            3217,
            "BUSINESS_ERROR_03217", 
            "���ʂR��t�敪������`�̒l�ł��B");

    /**
     * ���ʂS��t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03218 = errorMgr.defineErrorInfo(
            3218,
            "BUSINESS_ERROR_03218", 
            "���ʂS��t�敪������`�̒l�ł��B");

    /**
     * ���ʂT��t�敪������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03219 = errorMgr.defineErrorInfo(
            3219,
            "BUSINESS_ERROR_03219", 
            "���ʂT��t�敪������`�̒l�ł��B");

    /**
     * �\���Ώۏ��ʂ��I������Ă��܂���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03220 = errorMgr.defineErrorInfo(
            3220,
            "BUSINESS_ERROR_03220", 
            "�\���Ώۏ��ʂ��I������Ă��܂���B");

    /**
     * �d�q��t�\���t���O�������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03221 = errorMgr.defineErrorInfo(
            3221,
            "BUSINESS_ERROR_03221", 
            "�d�q��t�\���t���O�������͂ł��B");

    /**
     * �d�q��t�\���t���O������`�̒l�ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03222 = errorMgr.defineErrorInfo(
            3222,
            "BUSINESS_ERROR_03222", 
            "�d�q��t�\���t���O������`�̒l�ł��B");

    /**
     * ��t�Ώۂ������͂ł��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03223 = errorMgr.defineErrorInfo(
            3223,
            "BUSINESS_ERROR_03223", 
            "��t�Ώۂ������͂ł��B");

    /**
     * ���O�C����~���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90201 = errorMgr.defineErrorInfo(
            90201,
            "BUSINESS_ERROR_90201", 
            "�������܃����e�i���X���ׁ̈A���O�C���ł��܂���B");

    /**
     * �ڋq���O�C���G���[�񐔏�����߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90202 = errorMgr.defineErrorInfo(
            90202,
            "BUSINESS_ERROR_90202", 
            "�\���󂠂�܂��񂪁A���݃��O�C���ł��Ȃ���Ԃł��B�R�[���Z���^�[�ɂ��⍇���������B");

    /**
     * �p�X���[�h�l�s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90203 = errorMgr.defineErrorInfo(
            90203,
            "BUSINESS_ERROR_90203", 
            "���O�C���ł��܂���ł����B�p�X���[�h�����m�F�̏�A�ēx���O�C�����ĉ������B");

    /**
     * �w��ڋq�ɐ��肷�܂������Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90204 = errorMgr.defineErrorInfo(
            90204,
            "BUSINESS_ERROR_90204", 
            "�w��ڋq�ɐ��肷�܂��ł��܂���B�Ǘ��҂ɂ��⍇���������B");

    /**
     * �w�萬�肷�܂��ڋq�Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90205 = errorMgr.defineErrorInfo(
            90205,
            "BUSINESS_ERROR_90205", 
            "�w��̌ڋq�f�[�^�����݂��Ȃ��ׁA���肷�܂��ł��܂���B");

    /**
     * CC�I�y���[�^���O�C���G���[�񐔏�����߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90206 = errorMgr.defineErrorInfo(
            90206,
            "BUSINESS_ERROR_90206", 
            "�\���󂠂�܂��񂪁A���݃��O�C���ł��Ȃ���Ԃł��B�Ǘ��҂ɂ��⍇���������B");

    /**
     * ���肷�܂��Ώۂ̌ڋq�R�[�h�l�s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90207 = errorMgr.defineErrorInfo(
            90207,
            "BUSINESS_ERROR_90207", 
            "�ڋq�R�[�h�l���s���ł��B���m�F�̏�A�ēx���肷�܂������s���ĉ������B");

    /**
     * �u����v�Ŏ�M�����n�b�V���l���v�Z�l�ƈ�v���Ȃ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90208 = errorMgr.defineErrorInfo(
            90208,
            "BUSINESS_ERROR_90208", 
            "��ϐ\���󂠂�܂��񂪁A������o���Ȃ���Ԃł��B�R�[���Z���^�[�ɂ��⍇���������B");

    /**
     * �X�����O�V���b�g�����p�\�łȂ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90209 = errorMgr.defineErrorInfo(
            90209,
            "BUSINESS_ERROR_90209", 
            "���̃T�[�r�X�͌��ݗ��p�\�ɂȂ��Ă���܂���B�L�����o�^���s������ɗ��p�\�ƂȂ�܂��B");

    /**
     * �p�X���[�h�ύX���̋��p�X���[�h���s���B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90210 = errorMgr.defineErrorInfo(
            90210,
            "BUSINESS_ERROR_90210", 
            "�p�X���[�h���ύX�ł��܂���ł����B�R�[���Z���^�[�ɂ��⍇���������B");

    /**
     * �V�p�X���[�h�Ƃ��ĂQ�x���͂������̂���v���Ȃ������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90211 = errorMgr.defineErrorInfo(
            90211,
            "BUSINESS_ERROR_90211", 
            "�V�p�X���[�h���m�F�p�̂��̂ƈ�v���Ă���܂���B���m�F�̏�A�ēx���͂��ĉ������B");

    /**
     * �p�X���[�h���ύX�������Ă��Ȃ����X�őO��Ɠ����p�X���[�h�����͂��ꂽ�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90212 = errorMgr.defineErrorInfo(
            90212,
            "BUSINESS_ERROR_90212", 
            "�p�X���[�h���ς���Ă��܂���B�Ⴄ�p�X���[�h����͂��ĉ������B");

    /**
     * �ڋq���肷�܂����Ƀp�X���[�h����v���Ȃ������B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90213 = errorMgr.defineErrorInfo(
            90213,
            "BUSINESS_ERROR_90213", 
            "�p�X���[�h���Ⴄ�ׁA���肷�܂��ł��܂���B���m�F�̏�A�ēx���s���ĉ������B");

    /**
     * �i�p�X���[�h�ύX�j�V�p�X���[�h�l���Ó��łȂ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90214 = errorMgr.defineErrorInfo(
            90214,
            "BUSINESS_ERROR_90214", 
            "�V�p�X���[�h�ɋ�����Ă��Ȃ��������܂܂�Ă��܂��B���m�F�̏�A�ēx�p�X���[�h�ύX���s���ĉ������B");

    /**
     * �Ǘ��҃��O�C���G���[�񐔏�����߁B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90215 = errorMgr.defineErrorInfo(
            90215,
            "BUSINESS_ERROR_90215", 
            "�\���󂠂�܂��񂪁A���݃��O�C���ł��Ȃ���Ԃł��B���̊Ǘ��҂ɂ��⍇���������B");

    /**
     * �i�p�X���[�h�ύX�j�V�p�X���[�h�����͈͊O�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90216 = errorMgr.defineErrorInfo(
            90216,
            "BUSINESS_ERROR_90216", 
            "�V�p�X���[�h�̒������s���ł��B���m�F�̏�A�ēx�p�X���[�h�ύX���s���ĉ������B");

    /**
     * �i�p�X���[�h�ύX�j�V�p�X���[�h�����݃p�X���[�h�Ɠ����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90217 = errorMgr.defineErrorInfo(
            90217,
            "BUSINESS_ERROR_90217", 
            "�p�X���[�h���ς���Ă��܂���B���݂ƈႤ�p�X���[�h����͂��ĉ������B");

    /**
     * �i�p�X���[�h�ύX�j�V�p�X���[�h�����O�C�����Ɠ����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90218 = errorMgr.defineErrorInfo(
            90218,
            "BUSINESS_ERROR_90218", 
            "�p�X���[�h�����O�C�����Ɠ����ł��B���O�C�����Ƃ͈Ⴄ�p�X���[�h����͂��ĉ������B");

    /**
     * �i�p�X���[�h�ύX�j�V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɠ����B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90219 = errorMgr.defineErrorInfo(
            90219,
            "BUSINESS_ERROR_90219", 
            "���̃p�X���[�h�͉ߋ��R����ȓ��Ŏg���Ă��܂��B�ߋ��R����Ƃ͈Ⴄ�p�X���[�h����͂��ĉ������B");

    /**
     * �i�p�X���[�h�ύX�j�V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɨގ��B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90220 = errorMgr.defineErrorInfo(
            90220,
            "BUSINESS_ERROR_90220", 
            "���̃p�X���[�h�͉ߋ��R����ȓ��̂��̂Ɨގ����Ă��܂��B�ߋ��R����Ƃ͈Ⴄ�p�X���[�h����͂��ĉ������B");

    /**
     * ���̑��F�؃G���[�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90221 = errorMgr.defineErrorInfo(
            90221,
            "BUSINESS_ERROR_90221", 
            "���O�C���ł��܂���ł����B���͓��e�����m�F�̏�A�ēx���O�C�����ĉ������B");

    /**
     * �ڋq�R�[�h�̑Ó������`���b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90222 = errorMgr.defineErrorInfo(
            90222,
            "BUSINESS_ERROR_90222", 
            "�ڋq�R�[�h�̒l���s���ł��B");

    /**
     * SHA1�F�؃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90223 = errorMgr.defineErrorInfo(
            90223,
            "BUSINESS_ERROR_90223", 
            "SHA1�F�؃L�[������������܂���B���O�C�����s���܂����B");

    /**
     * �n�b�V���F�؃`�F�b�N�B<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90224 = errorMgr.defineErrorInfo(
            90224,
            "BUSINESS_ERROR_90224", 
            "�n�b�V���F�؃G���[�B");

    /**
     * �ڋq�d���o�^�̉\������B<br>
     */
     public static final ErrorInfo WARNING_60001 = errorMgr.defineErrorInfo(
            60001,
            "WARNING_60001", 
            "�ڋq�d���o�^�̉\������B");

    /**
     * Y�q�̉\������B<br>
     */
     public static final ErrorInfo WARNING_60002 = errorMgr.defineErrorInfo(
            60002,
            "WARNING_60002", 
            "Y�q�̉\������B");

    /**
     * �ڋq�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60003 = errorMgr.defineErrorInfo(
            60003,
            "WARNING_60003", 
            "�ڋq�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * �Z���P�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60004 = errorMgr.defineErrorInfo(
            60004,
            "WARNING_60004", 
            "�Z���P�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * �Z���Q�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60005 = errorMgr.defineErrorInfo(
            60005,
            "WARNING_60005", 
            "�Z���Q�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * �Z���R�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60006 = errorMgr.defineErrorInfo(
            60006,
            "WARNING_60006", 
            "�Z���R�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * �Z���P�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60007 = errorMgr.defineErrorInfo(
            60007,
            "WARNING_60007", 
            "�Z���P�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * �Z���Q�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60008 = errorMgr.defineErrorInfo(
            60008,
            "WARNING_60008", 
            "�Z���Q�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * �Z���R�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60009 = errorMgr.defineErrorInfo(
            60009,
            "WARNING_60009", 
            "�Z���R�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * �Z�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<br>
     */
     public static final ErrorInfo WARNING_60010 = errorMgr.defineErrorInfo(
            60010,
            "WARNING_60010", 
            "�Z�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");

    /**
     * ���[���A�h���X�d���B<br>
     */
     public static final ErrorInfo WARNING_60011 = errorMgr.defineErrorInfo(
            60011,
            "WARNING_60011", 
            "���[���A�h���X�d���o�^�̉\������B");

    /**
     * �d�b�ԍ��d���B<br>
     */
     public static final ErrorInfo WARNING_60012 = errorMgr.defineErrorInfo(
            60012,
            "WARNING_60012", 
            "�d�b�ԍ��d���o�^�̉\������B");

    /**
     * �g�ѓd�b�ԍ��d���B<br>
     */
     public static final ErrorInfo WARNING_60013 = errorMgr.defineErrorInfo(
            60013,
            "WARNING_60013", 
            "�g�ѓd�b�ԍ��d���o�^�̉\������B");

            
    /**
     * �R���X�g���N�^�B
     */
    private WEB3ErrorCatalog()
    {
    }
}

@
