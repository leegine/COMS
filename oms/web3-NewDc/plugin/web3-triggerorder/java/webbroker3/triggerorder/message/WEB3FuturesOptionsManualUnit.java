head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOptionsManualUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�蓮����Unit(WEB3FuturesOptionsManualUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
                 : 2006/08/24  ������(���u) ���f��No.163 ����
*/

package webbroker3.triggerorder.message;

import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;

/**
 * (�敨OP�蓮����Unit)<BR>
 * �敨OP�蓮����Unit�N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3FuturesOptionsManualUnit extends WEB3ManualCommonUnit 
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
     * 0005�F�@@TOPIX<BR>
     * 0018�F�@@���o225<BR>
     * 0016�F�@@���o300<BR>
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
     * P�F�v�b�g�I�v�V����<BR>
     * C�F�R�[���I�v�V����<BR>
     * <BR>
     * ���敨�̏ꍇ�́Anull���Z�b�g�B<BR>
     */
    public String opProductType = null;
    
    /**
     * (���������P���敪)<BR>
     * ���������P���敪<BR>
     * <BR>
     * 0�F�@@�����Y���i<BR>
     * 1�F�@@�v���~�A��<BR>
     * <BR>
     * ������������ʂ�"�t�w�l����"�܂���"�v�w�l����"�̏ꍇ�ݒ�<BR>
     */
    public String orderCondPriceDiv = null;
    
    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[�B<BR>
     */
    public String[] messageSuspension = null;
    
    /**
     * (���ʖ���)<BR>
     * ���ʖ���<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
     * (�敨OP�蓮����Unit)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 43F48892038A
     */
    public WEB3FuturesOptionsManualUnit() 
    {
     
    }
}
@
