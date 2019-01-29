head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������c���Ɖ��(WEB3EquityBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;


/**
 * �i���������c���Ɖ�ׁj�B<BR>
 * <BR>
 * ���������c���Ɖ�׃N���X<BR>
 */
public class WEB3EquityBalanceReferenceDetailUnit extends WEB3EquityAssetUnit
{
    
    /**
     * (�c������)<BR>
     *<BR>
     * �c������<BR>
     */
    public String balanceQuantity;
    
    /**
     * (���t�s�\����)<BR>
     *<BR>
     * ���t�s�\����<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (�T�Z�뉿�P��)<BR>
     *<BR>
     * �T�Z�뉿�P��<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * (�뉿�P�����͍σt���O)<BR>
     *<BR>
     * �뉿�P�����͍σt���O<BR>
     * <BR>
     * false�F�@@������<BR>
     * true�F�@@���͍�<BR>
     */
    public boolean estimatedBookPriceInputFlag = false;
    
    /**
     * (����)<BR>
     *<BR>
     * ����<BR>
     */
    public String currentPrice = null;
    
    /**
     * (�O����)<BR>
     *<BR>
     * �O����<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (�����擾����)<BR>
     *<BR>
     * �����擾����<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (�D��s��R�[�h)<BR>
     *<BR>
     * �D��s��R�[�h<BR>
     */
    public String primaryMarketCode = null;
    
    /**
     * (�T�Z�]���z(�c������))<BR>
     *<BR>
     * �T�Z�]���z(�c������)<BR>
     */
    public String estimatedAssetBalanceQuantity;
    
    /**
     * (�T�Z�]���z(���t�\����))<BR>
     *<BR>
     * �T�Z�]���z(���t�\����)<BR>
     */
    public String estimatedAssetSellPossQuantity;
    
    /**
     * (�T�Z�]���z(����������))<BR>
     *<BR>
     * �T�Z�]���z(����������)<BR>
     */
    public String estimatedAssetOrderedQuantity;
    
    /**
     * (�T�Z�]���z(���t�s�\����))<BR>
     *<BR>
     * �T�Z�]���z(���t�s�\����)<BR>
     */
    public String estimatedAssetSellImpossQuantity;
    
    /**
     * (�T�Z�]�����v(�c������))<BR>
     *<BR>
     * �T�Z�]�����v(�c������)<BR>
     */
    public String estimatedAppraisalProfitLossBalanceQuantity = null;
    
    /**
     * (�T�Z�]�����v(���t�\����))<BR>
     *<BR>
     * �T�Z�]�����v(���t�\����)<BR>
     */
    public String estimatedAppraisalProfitLossSellPossQuantity = null;
    
    /**
     * (�T�Z�]�����v(����������))<BR>
     *<BR>
     * �T�Z�]�����v(����������)<BR>
     */
    public String estimatedAppraisalProfitLossOrderedQuantity = null;
    
    /**
     * (�T�Z�]�����v(���t�s�\����))<BR>
     *<BR>
     * �T�Z�]�����v(���t�s�\����)<BR>
     */
    public String estimatedAppraisalProfitLossSellImpossQuantity = null;
    
    /**
     * (���t�\�t���O)<BR>
     *<BR>
     * true�F���t�\�@@�@@false�F���t�s��<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �|���g�̑S���ڂ�null�ŏ���������B<BR>
     * @@roseuid 41B683410086<BR>
     */
    public WEB3EquityBalanceReferenceDetailUnit() 
    {
        this.balanceQuantity = null;
        this.sellImpossQuantity = null;
        this.estimatedAssetBalanceQuantity = null;
        this.estimatedAssetOrderedQuantity = null;
        this.estimatedAssetSellImpossQuantity = null;
        this.estimatedAssetSellPossQuantity = null;
        this.buyPossFlag = true;
        this.sellPossFlag = true;
    }
}
@
