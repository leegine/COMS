head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������c���Ɖ��(WEB3FeqBalanceReferenceDetailUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[     
*/

package webbroker3.feq.message;

/**
 * (�O�������c���Ɖ��)<BR>
 * �O�������c���Ɖ�׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBalanceReferenceDetailUnit extends WEB3FeqAssetUnit 
{
    
    /**
     * (�c������)<BR>
     * �c������<BR>
     */
    public String balanceQuantity;
    
    /**
     * (���t�s�\����)<BR>
     * ���t�s�\����<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (�T�Z�뉿�P���i�~�݁j�j<BR>
     * �T�Z�뉿�P���i�~�݁j<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (�T�Z�뉿�P���i�O�݁j)<BR>
     * �T�Z�뉿�P���i�O�݁j<BR>
     */
    public String foreignEstimatedBookPrice;
    
    /**
     * (�뉿�P�����͍σt���O)<BR>
     * �뉿�P�����͍σt���O<BR>
     * <BR>
     * false�F������<BR>
     * true�F���͍�<BR>
     */
    public boolean estimatedBookPriceInputFlag;
    
    /**
     * (����)<BR>
     * ����<BR>
     * <BR>
     * ���l�����Ă��Ȃ��Ƃ��͖��ݒ�<BR>
     */
    public String currentPrice;
    
    /**
     * (�O����)<BR>
     * �O����<BR>
     * <BR>
     * ���l�����Ă��Ȃ��Ƃ��͖��ݒ�<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (�����擾����)<BR>
     * �����擾����<BR>
     * <BR>
     * ���l�����Ă��Ȃ��Ƃ��͖��ݒ�<BR>
     */
    public String currentPriceTime;
    
    /**
     * (�T�Z�]���z(�c������))<BR>
     * �T�Z�]���z(�c������)<BR>
     */
    public String estimatedAssetBalanceQuantity;
    
    /**
     * (�T�Z�]���z(���t�\����))<BR>
     * �T�Z�]���z(���t�\����)<BR>
     */
    public String estimatedAssetSellPossQuantity;
    
    /**
     * (�T�Z�]���z(����������))<BR>
     * �T�Z�]���z(����������)<BR>
     */
    public String estimatedAssetOrderedQuantity;
    
    /**
     * (�T�Z�]���z(���t�s�\����))<BR>
     * �T�Z�]���z(���t�s�\����)<BR>
     */
    public String estimatedAssetSellImpossQuantity;
    
    /**
     * (�T�Z�]�����v(�c������))<BR>
     * �T�Z�]�����v(�c������)<BR>
     */
    public String estimatedAppraisalProfitLossBalanceQuantity;
    
    /**
     * (�T�Z�]�����v(���t�\����))<BR>
     * �T�Z�]�����v(���t�\����)<BR>
     */
    public String estimatedAppraisalProfitLossSellPossQuantity;
    
    /**
     * (�T�Z�]�����v(����������))<BR>
     * �T�Z�]�����v(����������)<BR>
     */
    public String estimatedAppraisalProfitLossOrderedQuantity;
    
    /**
     * (�T�Z�]�����v(���t�s�\����))<BR>
     * �T�Z�]�����v(���t�s�\����)<BR>
     */
    public String estimatedAppraisalProfitLossSellImpossQuantity;
    
    /**
     * (���t�\�t���O)<BR>
     * ���t�\�t���O<BR>
     * <BR>
     * true�F���t�\<BR>
     * false�F���t�s��<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * (�O�������c���Ɖ��)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 42A7FB0D0145
     */
    public WEB3FeqBalanceReferenceDetailUnit() 
    {
     
    }
}
@
