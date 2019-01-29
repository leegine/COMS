head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformColumnModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A�����J�������f���N���X(WEB3AdminInformColumnModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/21 ������(���u) �쐬
*/

package webbroker3.inform;

import java.text.DateFormat;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;

/**
 * (�A�����J�������f��)<BR>
 * �A�����J�������f���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformColumnModel extends WEB3GentradeCsvColumnModel 
{
    /**
     * (���͍��ڕ�����)<BR>
     * ���͍��ڕ�����
     */
    private String inputItemSymbolName;
    
    /**
     * (�A�����ڃf���~�b�^)<BR>
     * �A�����ڃf���~�b�^
     */
    private String catDelimitter;
    
    /**
     * (�Z�N�V�����ԍ�)<BR>
     * �Z�N�V�����ԍ�
     */
    private String sectionNumber;
    
    /**
     * @@roseuid 41EE642D0271
     */
    public WEB3AdminInformColumnModel() 
    {
     
    }
    
    /**
     * (�A�����J�������f��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�jsuper()���R�[������B<BR>
     * <BR>
     *   [super()�̈���] <BR>
     *   ���ڃ��x���F ����.���ڃ��x�� <BR>
     *   �J�����ԍ��F ����.�J�����ԍ� <BR>
     *   ���ڌ^�F ����.���ڌ^ <BR>
     *   ���t�t�H�[�}�b�g�F ����.���t�t�H�[�}�b�g <BR>
     * <BR>
     * �Q�j����.���͍��ڕ������A����.�A�����ڃf���~�b�^�A<BR>
     * ����.�Z�N�V�����ԍ������g�̃v���p�e�B�ɃZ�b�g����B
     * @@param l_strItemLevel - (���ڃ��x��)<BR>
     * ���ڃ��x��<BR>
     * 
     * @@param l_intColumn - (�J�����ԍ�)<BR>
     * �J�����ԍ�<BR>
     * 
     * @@param l_intItemType - (���ڌ^)<BR>
     * ���ڌ^<BR>
     * 
     * @@param l_dateFormat - (���t�t�H�[�}�b�g)<BR>
     * ���t�t�H�[�}�b�g<BR>
     * 
     * @@param l_strInputItemSymbolName - (���͍��ڕ�����)<BR>
     * ���͍��ڕ�����
     * 
     * @@param l_strConnectItemDelimiter - (�A�����ڃf���~�b�^)<BR>
     * �A�����ڃf���~�b�^
     * 
     * @@param l_strSectionNumber - (�Z�N�V�����ԍ�)<BR>
     * �Z�N�V�����ԍ�
     * @@roseuid 41BED30A03A7
     */
    public WEB3AdminInformColumnModel(
        String l_strItemLevel, 
        int l_intColumn, 
        int l_intItemType, 
        DateFormat l_dateFormat, 
        String l_strInputItemSymbolName, 
        String l_strConnectItemDelimiter, 
        String l_strSectionNumber) 
    {
        //�P�jsuper()���R�[������B<BR>
        super(l_strItemLevel,
            l_intColumn,
            l_intItemType,
            l_dateFormat);
                    
        //�Q�j����.���͍��ڕ����������g�̃v���p�e�B�ɃZ�b�g����
        this.inputItemSymbolName = l_strInputItemSymbolName;

        // ����.�A�����ڃf���~�b�^�����g�̃v���p�e�B�ɃZ�b�g����
        this.catDelimitter = l_strConnectItemDelimiter;

        // ����.�Z�N�V�����ԍ������g�̃v���p�e�B�ɃZ�b�g����
        this.sectionNumber = l_strSectionNumber;
    }
    
    /**
     * (get���͍��ڕ�����)<BR>
     * ���͍��ڕ��������擾����B<BR>
     * <BR>
     * this.���͍��ڕ�������ԋp����B<BR>
     * @@return String
     * @@roseuid 41BED4310155
     */
    public String getInputItemSymbolName() 
    {
        return this.inputItemSymbolName;
    }
    
    /**
     * (get�A�����ڃf���~�b�^)<BR>
     * �A�����ڃf���~�b�^���擾����B<BR>
     * <BR>
     * this.�A�����ڃf���~�b�^��ԋp����B<BR>
     * @@return String
     * @@roseuid 41BED4390397
     */
    public String getCatDelimitter() 
    {
        return this.catDelimitter;
    }
    
    /**
     * (get�Z�N�V�����ԍ�)<BR>
     * �Z�N�V�����ԍ����擾����B<BR>
     * <BR>
     * this.�Z�N�V�����ԍ���ԋp����B<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getSectionNumber() 
    {
        return this.sectionNumber;
    }
}
@
