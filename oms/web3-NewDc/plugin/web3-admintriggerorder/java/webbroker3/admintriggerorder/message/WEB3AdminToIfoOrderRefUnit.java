head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�Unit(WEB3AdminToIfoOrderRefUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15�@@�]�V�q(���u) �V�K�쐬
                 : 2006/08/23�@@�юu��(���u) �d�l�ύXNo.66
*/

package webbroker3.admintriggerorder.message;

/**
 * (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�Unit)<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefUnit extends WEB3AdminToOrderRefCommonUnit
{
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@�I�v�V������������ <BR>
     * 1�F�@@�敨�I�v�V��������<BR>
     */
    public String taxType;
    
    /**
     * (�w�����)<BR>
     * �w�����<BR>
     * <BR>
     * 0005�F�@@TOPIX <BR>
     * 0018�F�@@���o225 <BR>
     * 0016�F�@@���o300 <BR>
     * 0019�F�@@�~�j���o225<BR>
     */
    public String targetProductCode;
    
    /**
     * (����)<BR>
     * ����<BR>
     * (YYYYMM)<BR>
     */
    public String delivaryMonth;
    
    /**
     * (�s�g���i)<BR>
     * �s�g���i<BR>
     */
    public String strikePrice = null;
    
    /**
     * (�I�v�V�������i�敪)<BR>
     * �I�v�V�������i�敪<BR>
     * <BR>
     * P�F�v�b�g�I�v�V���� <BR>
     * C�F�R�[���I�v�V���� <BR>
     * <BR>
     * ���敨�̏ꍇ�́Anull���Z�b�g�B<BR>
     */
    public String opProductType = null;
    
    /**
     * (���������P���敪)<BR>
     * ���������P���敪<BR>
     * <BR>
     * 0�F�@@�����Y���i�@@<BR>
     * 1�F�@@�v���~�A�� <BR>
     * <BR>
     * ������������ʂ�"�t�w�l����"�܂���"�v�w�l"�̏ꍇ�ݒ�<BR>
     */
    public String orderCondPriceDiv = null;
    
    /**
     * (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�Unit)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 43E9BEE0000A
     */
    public WEB3AdminToIfoOrderRefUnit() 
    {
     
    }
}
@
