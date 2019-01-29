head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeCsvColumnModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CSV�J�������f��(WEB3GentradeCsvColumnModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 �� ��(���u) �V�K�쐬
*/

package webbroker3.gentrade;

import java.text.DateFormat;

/**
 * (CSV�J�������f��) <BR>
 * �iWEB3CsvColumnModel�j<BR>
 * <BR>
 * CVS�t�@@�C���̗�i�J�����j��\������N���X�B<BR>
 * CVS�f�[�^���f���N���X�ɂĎg�p����B<BR>
 */
public class WEB3GentradeCsvColumnModel
{

    /**
     * (���ڌ^_������) <BR>
     * �萔��`�v���p�e�B <BR>
     * <BR>
     * ������^�ijava.lang.String�j������int�l���`����B <BR>
     */
    public static int STRINGTYPE = 0;

    /**
     * (���ڌ^_���l)�iint�j <BR>
     * �萔��`�v���p�e�B <BR>
     * <BR>
     * ���l�^�ijava.lang.Integer�j������int�l���`����B<BR>
     */
    public static int INTEGERTYPE = 10;

    /**
     * (���ڌ^_���l)�ilong�j <BR>
     * �萔��`�v���p�e�B <BR>
     * <BR>
     * ���l�^�ijava.lang.Long�j������int�l���`����B<BR>
     */
    public static int LONGTYPE = 11;

    /**
     * (���ڌ^_���l)�idouble�j <BR>
     * �萔��`�v���p�e�B <BR>
     * <BR>
     * ���l�^�ijava.lang.Double�j������int�l���`����B <BR>
     */
    public static int DOUBLETYPE = 12;

    /**
     * (���ڌ^_���t) <BR>
     * �萔��`�v���p�e�B <BR>
     * <BR>
     * ���t�^�ijava.util.Date�j������int�l���`����B<BR>
     */
    public static int DATETYPE = 20;

    /**
     * (���ڌ^_���t����) <BR>
     * �萔��`�v���p�e�B <BR>
     * <BR>
     * ���t���Ԍ^�ijava.util.Date�Ajava.sql.Timestamp�j��<BR>
     * ����int�l���`����B<BR>
     */
    public static int TIMESTAMPTYPE = 21;

    /**
     * (���ڃ��x��) <BR>
     * �ΏۃJ�����̃^�C�g��������<BR>
     */
    private String columnLabel;

    /**
     * (�J�����ԍ�) <BR>
     * �ΏۃJ�����̃J�����ԍ��B<BR>
     * <BR>
     * ���@@0 Origin�ɂăZ�b�g����B<BR>
     * ���@@CSV�f�[�^���f��.�J�����w�b�_�z��̗v�f�ԍ��Ɠ����l���Z�b�g����B<BR>
     */
    private int columnNumber;

    /**
     * (���ڌ^) <BR>
     * �ΏۃJ�����̃f�[�^�^�������t���O <BR>
     * <BR>
     * ���@@�ȉ��̉��ꂩ���Z�b�g����B<BR>
     * �@@CSV�񃂃f��.���ڌ^_������ <BR>
     * �@@CSV�񃂃f��.���ڌ^_���l�iint�j <BR>
     * �@@CSV�񃂃f��.���ڌ^_���l�ilong�j <BR>
     * �@@CSV�񃂃f��.���ڌ^_���l�idouble�j <BR>
     * �@@CSV�񃂃f��.���ڌ^_���t <BR>
     * �@@CSV�񃂃f��.���ڌ^_���t���� <BR>
     */
    private int columnType;

    /**
     * (���t�t�H�[�}�b�g) <BR>
     * �ΏۃJ�����̃f�[�^�̍��ڌ^���u���t�v�A<BR>
     * �܂��́u���t���ԁv�̏ꍇ�Ɏw�肷��DateFormat�B<BR>
     * <BR>
     * ���@@���ڌ^���u���t�v�A�u���t���ԁv�łȂ��ꍇ��null�B<BR>
     */
    private DateFormat dateFormat;

    /**
     * @@roseuid 41076DC702DE
     */
    public WEB3GentradeCsvColumnModel()
    {

    }

    /**
     * (CSV�񃂃f��) <BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̒l���v���p�e�B�ɃZ�b�g����B<BR>
     * ���������C���X�^���X��ԋp����B<BR>
     * @@param l_strColumnLabel - ���ڃ��x�� <BR>
     * �ΏۃJ�����̃^�C�g��������<BR>
     * @@param l_intColumnNumber - �J�����ԍ� <BR>
     * �ΏۃJ�����̃J�����ԍ��B<BR>
     * <BR>
     * ���@@0 Origin�ɂăZ�b�g����B<BR>
     * ���@@CSV�f�[�^���f��.�J�����w�b�_�z��̗v�f�ԍ��Ɠ����l���Z�b�g����B<BR>
     * 
     * @@param l_intColumnType - ���ڌ^ <BR>
     * �ΏۃJ�����̃f�[�^�^�������t���O <BR>
     * <BR>
     * ���@@�ȉ��̉��ꂩ�̒l���w�肷��B<BR>
     * �@@CSV�񃂃f��.���ڌ^_������ <BR>
     * �@@CSV�񃂃f��.���ڌ^_���l <BR>
     * �@@CSV�񃂃f��.���ڌ^_���t <BR>
     * �@@CSV�񃂃f��.���ڌ^_���t���� <BR>
     * 
     * @@param l_dateFormat - ���t�t�H�[�}�b�g <BR>
     * �ΏۃJ�����̃f�[�^�̍��ڌ^���u���t�v�A<BR>
     * �܂��́u���t���ԁv�̏ꍇ�Ɏw�肷��DateFormat�B<BR>
     * <BR>
     * ���@@���ڌ^���u���t�v�A�u���t���ԁv�łȂ��ꍇ��null���w�肷��B<BR>
     * 
     * 
     * @@return webbroker3.�Ɩ����[�e�B���e�B.WEB3CsvColumnModel
     * @@roseuid 40E2506A0047
     */
    public WEB3GentradeCsvColumnModel(
        String l_strColumnLabel,
        int l_intColumnNumber,
        int l_intColumnType,
        DateFormat l_dateFormat)
    {
        this.columnLabel = l_strColumnLabel;
        this.columnNumber = l_intColumnNumber;
        this.columnType = l_intColumnType;
        this.dateFormat = l_dateFormat;
    }

    /**
     * (get���ڃ��x��) <BR>
     * this.���ڃ��x����ԋp����B<BR>
     * @@return java.lang.String
     * @@roseuid 40E249FF00B4
     */
    public String getColumnLabel()
    {
        return this.columnLabel;
    }

    /**
     * (get�J�����ԍ�) <BR>
     * this.�J�����ԍ���ԋp����B<BR>
     * @@return int
     * @@roseuid 40E2501E017F
     */
    public int getColumnNumber()
    {
        return this.columnNumber;
    }

    /**
     * (get���ڌ^) <BR>
     * this.���ڌ^��ԋp����B<BR>
     * @@return int
     * @@roseuid 40E2503D02E7
     */
    public int getColumnType()
    {
        return this.columnType;
    }

    /**
     * (get���t�t�H�[�}�b�g) <BR>
     * this.���t�t�H�[�}�b�g��ԋp����B<BR>
     * @@return java.text.DateFormat
     * @@roseuid 40E2504E02F6
     */
    public DateFormat getDateFormat()
    {
        return this.dateFormat;
    }
}
@
