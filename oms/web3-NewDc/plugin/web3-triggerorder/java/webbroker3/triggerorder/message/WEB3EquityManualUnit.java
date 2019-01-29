head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮����Unit(WEB3EquityManualUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@鰐V(���u) �V�K�쐬
                   2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;

/**
 * (�����蓮����Unit)<BR>
 * �����蓮����Unit<BR>
 * 
 * @@author 鰐V
 * @@version 1.0
 */
public class WEB3EquityManualUnit extends WEB3ManualCommonUnit 
{
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0:���<BR>
     * 1:����<BR>
     * 5:�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;
    
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
     * (�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P��<BR> 
     */
    public String estimatedBookPrice;
    
    /**
     * (����)<BR>
     * ����<BR>
     * �M�p����敪����ʐM�p�̎��ݒ�<BR>
     */
    public String interestRates;
    
    /**
     * (���Z����)<BR>
     * ���Z����<BR>
     * ��ʐM�p���ٍϊ������������̏ꍇ�ɕ\��<BR>
     */
    public String clearUpTerm;
    
    /**
     * (�ٍ�)<BR>
     * �ٍ�<BR>
     * �M�p����̏ꍇ�ݒ�<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (�M�p�����������)<BR>
     * �M�p�����������<BR>
     * <BR>
     * ���ԍώ蓮�����̏ꍇ�ݒ�<BR>
     */
    public WEB3MarginContractUnit[] contractUnits;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920196
     */
    public WEB3EquityManualUnit() 
    {
     
    }
}
@
