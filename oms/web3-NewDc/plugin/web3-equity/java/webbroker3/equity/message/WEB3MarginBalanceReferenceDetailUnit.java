head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����c���Ɖ��(WEB3MarginBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;


/**
 * �i�M�p����c���Ɖ�ׁj�B<BR>
 * <BR>
 * �M�p����c���Ɖ�׃N���X<BR>
 */
public class WEB3MarginBalanceReferenceDetailUnit extends WEB3MarginContractReferenceUnit 
{
    
    /**
     * (����)<BR>
     *<BR>
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
     * (�V�K���\�t���O)<BR>
     * <BR>
     * true�F�V�K���\�@@false�F�V�K���s��<BR>
     */
    public boolean tradingFlag;
    
    /**
     * (�]�����v�i���o��l���j)<BR>
     * <BR>
     * ���o��������������]�����v<BR>
     */
    public String appraisalProfitLossCost = null;
    
    /**
     * �R���X�g���N�^�B<BR>
     * @@roseuid 41C189020071<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit() 
    {
     
    }
}
@
