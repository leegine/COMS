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
filename	WEB3GentradeTotalContractSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʏW�vSpec(WEB3GentradeTotalContractSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24 羐� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

/**
 * (���ʏW�vSpec)
 * ���ʏW�v�̏���\���N���X�B
 */
public class WEB3GentradeTotalContractSpec
{

    /**
     * �����R�[�h
     */
    public String productCode;

    /**
     * ��������
     */
    public double marginLongNum;

    /**
     * ��������
     */
    public double marginShortNum;

    /**
     * �R���X�g���N�^�B<BR>
     * �����̒l�𓯃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_strProductCode - �����R�[�h
     * @@param l_dblMarginLongNum - ��������
     * @@param l_dblMarginShortNum - ��������
     * @@roseuid 412D70BB02BE
     */
    public WEB3GentradeTotalContractSpec(
        String l_strProductCode,
        double l_dblMarginLongNum,
        double l_dblMarginShortNum)
    {
        this.productCode = l_strProductCode;
        this.marginLongNum = l_dblMarginLongNum;
        this.marginShortNum = l_dblMarginShortNum;
    }
}
@
