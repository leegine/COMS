head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE���������Ɖ�Unit (WEB3AdminToEquityOrderRefUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03�@@鰐V(���u) �V�K�쐬
                 : 2006/10/23  ������(���u)���f��No.085
*/

package webbroker3.admintriggerorder.message;

/**
 * (�g���K�[�����Ǘ��ҁE���������Ɖ�Unit )<BR>
 * 
 * @@author 鰐V<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminToEquityOrderRefUnit extends WEB3AdminToOrderRefCommonUnit
{
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F���<BR>
     * 1�F����<BR>
     * 5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * <BR>
     * 1�F���x�M�p
     * 2�F��ʐM�p<BR>
     */
    public String repaymentDiv;

    /**
     * (�l�i����)<BR>
     * �l�i����<BR>
     * <BR>
     * 0:�w��Ȃ�<BR>
     * 1:���ݒl�w�l<BR>
     * 3:�D��w�l<BR>
     * 5:���s�c���w�l<BR>
     * 7:���s�c�����<BR>
     */
    public String priceCondType;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C9007D
     */
    public WEB3AdminToEquityOrderRefUnit() 
    {
     
    }

}
@
