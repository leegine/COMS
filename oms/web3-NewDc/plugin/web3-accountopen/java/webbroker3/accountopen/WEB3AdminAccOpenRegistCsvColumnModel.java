head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenRegistCsvColumnModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\���J�������f��(WEB3AdminAccOpenRegistCsvColumnModel.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen;

import java.text.DateFormat;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;


/**
 * (�����J�ݐ\���J�������f��)<BR>
 * �����J�ݐ\���J�������f��<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminAccOpenRegistCsvColumnModel extends WEB3GentradeCsvColumnModel 
{
    
    /**
     * (���͍��ڕ�����)<BR>
     * ���͍��ڕ�����<BR>
     */
    private String inputItemSymbolName;
    
    /**
     * (�A�����ڃf���~�b�^)<BR>
     * �A�����ڃf���~�b�^<BR>
     * <BR>
     * 0�F�Ȃ�<BR>
     * 1�F���pSPACE<BR>
     * 2�F�S�pSPACE<BR>
     * 3�F�n�C�t���i�f-�f�j<BR>
     */
    private String catDelimitter;
    
    /**
     * (�Z�N�V�����ԍ�)<BR>
     * �Z�N�V�����ԍ�<BR>
     * <BR>
     * ���f���~�b�^�ŕ��������Z�N�V�����̉��Ԗڂ��o�͂��邩���A0����w�肷��B<BR>
     */
    private String sectionNo;
    
    /**
     * @@roseuid 41B45E6F02DE
     */
    public WEB3AdminAccOpenRegistCsvColumnModel() 
    {
     
    }
    
    /**
     * (�����J�ݐ\���J�������f��)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@super()���R�[������<BR>
     * <BR>
     * �@@[super()�̈���]<BR>
     * �@@���ڃ��x���F�@@���ڃ��x��<BR>
     * �@@�J�����ԍ��F �J�����ԍ�<BR>
     * �@@���ڌ^�F�@@���ڌ^<BR>
     * �@@���t�t�H�[�}�b�g�F�@@���t�t�H�[�}�b�g<BR>
     * <BR>
     * �Q�j�@@���͍��ڕ������C�A�����ڃf���~�b�^�C�Z�N�V�����ԍ������g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_strColumnLabel - �ΏۃJ�����̃^�C�g��������
     * @@param l_intColumnNumber - �ΏۃJ�����̃J�����ԍ��B<BR>
     * <BR>
     * ���@@0 Origin�ɂăZ�b�g����B<BR>
     * ���@@CSV�f�[�^���f��.�J�����w�b�_�z��̗v�f�ԍ��Ɠ����l���Z�b�g����B<BR>
     * 
     * @@param l_intColumnType - �ΏۃJ�����̃f�[�^�^�������t���O<BR>
     * <BR>
     * ���@@�ȉ��̉��ꂩ�̒l���w�肷��B<BR>
     * �@@CSV�񃂃f��.���ڌ^_������<BR>
     * �@@CSV�񃂃f��.���ڌ^_���l<BR>
     * �@@CSV�񃂃f��.���ڌ^_���t<BR>
     * �@@CSV�񃂃f��.���ڌ^_���t����<BR>
     * 
     * @@param l_dateFormat - �ΏۃJ�����̃f�[�^�̍��ڌ^���u���t�v�A�܂��́u���t���ԁv�̏ꍇ��<BR>
     * �w�肷��DateFormat�B<BR>
     * <BR>
     * ���@@���ڌ^���u���t�v�A�u���t���ԁv�łȂ��ꍇ��null���w�肷��B<BR>
     * <BR>
     * @@param l_strCatDelimitter - �A�����ڃf���~�b�^<BR>
     * <BR>
     * 0�F�Ȃ�<BR>
     * 1�F���pSPACE<BR>
     * 2�F�S�pSPACE<BR>
     * 3�F�n�C�t���i�f-�f�j<BR>
     * <BR>
     * @@param l_strSectionNo - �Z�N�V�����ԍ�<BR>
     *<BR>
     * ���f���~�b�^�ŕ��������Z�N�V�����̉��Ԗڂ��o�͂��邩���A0����w�肷��B<BR>
     * <BR>
     * @@param l_strInputItemSymbolName - ���͍��ڕ�����
     * @@return webbroker3.accountopen.WEB3AdminAccOpenRegistCsvColumnModel
     * @@roseuid 41A14CDF0039
     */
    public WEB3AdminAccOpenRegistCsvColumnModel(String l_strColumnLabel, int l_intColumnNumber, int l_intColumnType, DateFormat l_dateFormat, String l_strInputItemSymbolName, String l_strCatDelimitter, String l_strSectionNo) 
    {
        //�P�j�@@super()���R�[������
        super(l_strColumnLabel, l_intColumnNumber, l_intColumnType, l_dateFormat);
        
        //�Q�j�@@���͍��ڕ����������g�̃v���p�e�B�ɃZ�b�g����
        this.inputItemSymbolName = l_strInputItemSymbolName;
        this.catDelimitter = l_strCatDelimitter;
        this.sectionNo = l_strSectionNo;
    }
    
    /**
     * (get���͍��ڕ�����)<BR>
     * this.���͍��ڕ�������ԋp����B<BR>
     * @@return String
     * @@roseuid 41A14CB00172
     */
    public String getInputItemSymbolName() 
    {
        return this.inputItemSymbolName;
    }
    
    /**
     * (get�A�����ڃf���~�b�^)<BR>
     * this.�A�����ڃf���~�b�^���擾����B<BR>
     * @@return String
     * @@roseuid 41A436380289
     */
    public String getCatDelimitter() 
    {
        return this.catDelimitter;
    }
    
    /**
     * (get�Z�N�V�����ԍ�)<BR>
     * this.�Z�N�V�����ԍ���ԋp����B<BR>
     * @@return int
     * @@roseuid 41A4365E00E3
     */
    public String getSectionNo() 
    {
        return this.sectionNo;
    }
}
@
