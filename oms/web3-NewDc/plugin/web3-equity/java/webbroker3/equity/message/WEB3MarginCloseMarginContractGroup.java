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
filename	WEB3MarginCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ό����ꗗ���׍s(WEB3MarginCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p������ό����ꗗ���׍s�j�B<br>
 * <br>
 * �M�p������ό����ꗗ���׍s�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractGroup extends Message 
{
         
    /**
     * (����)<BR>
     * <BR>
     * YYYY/MM/DD�`���ŕ\��
     */
    public Date openDate;
    
    /**
     * (���P��)<BR>
     */
    public String contractPrice;
    
    /**
     * (��������)<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     */
    public String limitPrice;
    
    /**
     * (��芔��)
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     */
    public String execPrice;
    
    /**
     * (���萔��)<BR>
     */
    public String contractCommission;
    
    /**
     * (������)<BR>
     * ���ō�
     */
    public String interestFee;
    
    /**
     * (�t����)<BR>
     * ���ō�
     */
    public String payInterestFee;
    
    /**
     * (�݊���)<BR>
     * ���ō�
     */
    public String loanEquityFee;
    
    /**
     * (������)<BR>
     * ���ō�
     */
    public String setupFee;
    
    /**
     * (�Ǘ���)<BR>
     * ���ō�
     */
    public String managementFee;
    
    /**
     * (���̑�)<BR>
     * ���ō�
     */
    public String otherwise;
    
    /**
     * (���ϑ��v)<BR>
     */
    public String settleProfitLoss;
    
    /**
     * (���Ϗ���)<BR>
     */
    public String settlePriority;
    
    /**
     * @@roseuid 414032D000F4
     */
    public WEB3MarginCloseMarginContractGroup() 
    {
     
    }
}
@
