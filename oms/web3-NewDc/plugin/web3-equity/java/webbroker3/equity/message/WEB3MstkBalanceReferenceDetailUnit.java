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
filename	WEB3MstkBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c���Ɖ��(WEB3MstkBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;


/**
 * �i�����~�j�����c���Ɖ�ׁj�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�׃N���X<BR>
 */
public class WEB3MstkBalanceReferenceDetailUnit extends WEB3MstkSellUnit
{
    
    /**
     * (ID)<BR>
     * <BR>
     * �ۗL���YID<BR>
     */
    public String id;
    
    /**
     * (�����敪)<BR>
     * <BR>
     * 0�F��ʁ@@1�F����<BR>
     */
    public String taxType;
    
    /**
     * (�T�Z�뉿�P��)<BR>
     * <BR>
     * �T�Z�뉿�P��<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * (�뉿�P�����͍σt���O)<BR>
     * <BR>
     * false�F�@@������<BR>
     * true�F�@@���͍�<BR>
     */
    public boolean estimatedBookPriceInputFlag = false;
    
    /**
     * (����)<BR>
     * <BR>
     * ����<BR>
     */
    public String currentPrice = null;
    
    /**
     * (�O����)<BR>
     * <BR>
     * �O����<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (�����擾����)<BR>
     * <BR>
     * �����擾����<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (�T�Z�]���z)<BR>
     * <BR>
     * �T�Z�]���z<BR>
     */
    public String estimatedAsset;
    
    /**
     * (�T�Z�]�����v)<BR>
     * <BR>
     * �T�Z�]�����v<BR>
     */
    public String estimatedlAppraisalProfitLoss = null;
    
    /**
     * (���t�\�t���O)<BR>
     * <BR>
     * true�F���t�\�@@�@@false�F���t�s��<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * <BR>
     * @@roseuid 41C65B3400C0<BR>
     */
    public WEB3MstkBalanceReferenceDetailUnit() 
    {
     
    }
}
@
