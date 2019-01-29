head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTimeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ԃt�H�[�}�b�^�萔��`�C���^�t�F�C�X(WEB3GentradeTimeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/08 羐� (���u) �V�K�쐬
Revesion History : 2006/03/24 ������ (���u) �萔"HHMM00"��ǉ�
Revesion History : 2006/03/27 ������ (���u) �萔"HH:mm"��ǉ�
Revesion History : 2007/07/30 �h�C (���u) �萔"yyyyMM"��ǉ�
Revesion History : 2007/08/07 �h�C (���u) �萔"yyyy/MM/dd"��ǉ�
Revesion History : 2007/08/09 �h�C (���u) �萔"yyyy�NM��d���iE�j"��ǉ�
Revesion History : 2007/09/29 �h�C (���u) �萔"9999/12/31"��ǉ�
Revesion History : 2008/01/29 �h�C (���u) �萔"yyyy/M/d"�A"yyyy/M/d H:mm"�A"yyyy/MM/dd HH:mm:ss"��ǉ�
Revesion History : 2009/03/16 ��іQ (���u) �萔"MMdd"��ǉ�
*/
package webbroker3.gentrade.define;

/**
 * ���ԃt�H�[�}�b�^�萔��`�C���^�t�F�C�X
 *
 * @@author 羐�
 * @@version 1.0
 */
public interface WEB3GentradeTimeDef
{
    /**
      * �ŏ����̎��ԁB<BR>
      */
    public static final String MIN_RETURN = "000000";

    /**
      * �ő�̎��ԁB<BR>
      */
    public static final String MAX_RETURN = "235959";

    /**
      * �R�����B<BR>
      */
    public static final String STR_COLON = ":";

    /**
      * ���Ԃ̃t�H�[�}�b�^ �F�����B<BR>
      */
    public static final String TIME_FORMAT_HMS = "HHmmss";

    /**
      * ���Ԃ̃t�H�[�}�b�^ �F�����B<BR>
      */
    public static final String TIME_FORMAT_HM = "HHmm";

    /**
      * ���t�̃t�H�[�}�b�^ �F�N�B<BR>
      */
    public static final String DATE_FORMAT_YEAR = "yyyy";

    /**
     * ���t�̃t�H�[�}�b�^ �F�N�����B<BR>
    */
    public static final String DATE_FORMAT_YMD = "yyyyMMdd";

    /**
     * ���Ԃ̃t�H�[�}�b�^ �F�����b�B<BR>
     */
    public static final String TIME_FORMAT_HM0 = "HHmm00";

    /**
     * ���Ԃ̃t�H�[�}�b�^ �F��:���B<BR>
     */
    public static final String TIME_PARSE_HM = "HH:mm";

    /**
     * ���t�̃t�H�[�}�b�^ �F�N���B<BR>
    */
    public static final String DATE_FORMAT_YM = "yyyyMM";

    /**
     * ���Ԃ̃t�H�[�}�b�^ �F�N/��/���B<BR>
     */
    public static final String DATE_SPLIT_YMD = "yyyy/MM/dd";

    /**
     * ���Ԃ̃t�H�[�}�b�^ �F���������N���������i���j<BR>
     * ���ɂ́A�j��(��,��,��,��,��,��,�y)��ݒ肷��B<BR>
     */
    public static final String DATE_PARSE_YMDE = "yyyy�NM��d���iE�j";

    /**
      * �ő�����B<BR>
      */
    public static final String MAX_YMD = "9999/12/31";

    /**
      * ���t�̃t�H�[�}�b�^ �F�N/��/���B<BR>
      */
    public static final String DATE_FORMAT_YMD_SHORT = "yyyy/M/d";

    /**
      * ���Ԃ̃t�H�[�}�b�^ �F�N/��/�� ��:���B<BR>
      */
    public static final String TIME_FORMAT_YMDHM_SHORT = "yyyy/M/d H:mm";

    /**
      * ���Ԃ̃t�H�[�}�b�^ �F�N/��/�� ��:��:�b�B<BR>
      */
    public static final String TIME_FORMAT_YMDHMS = "yyyy/MM/dd HH:mm:ss";

    /**
     * ���t�̃t�H�[�}�b�^ �F�����B<BR>
     */
   public static final String DATE_FORMAT_MD = "MMdd";
}@
