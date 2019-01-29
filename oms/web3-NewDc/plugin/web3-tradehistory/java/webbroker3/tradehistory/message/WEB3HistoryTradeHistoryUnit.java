head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������(WEB3HistoryTradeHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27  �� �� �@@(���u) �V�K�쐬
                   2006/10/19  �����F (���u) ���f�� 057
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (����������)<BR>
 * ����������N���X<BR>
 * 
 * @@author �� �� �@@
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryUnit extends Message 
{
    /**
     * (�������ID)<BR>
     * �������ID<BR>
     */
    public String tradeHistoryId;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date execDate;
    
    /**
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p(*1)<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51:�@@���w���n�o<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     * ��null�̏ꍇ�́A"99:�@@���K"��\������B<BR>
     * (*1)"11:�@@�M�p"�̏ꍇ��this.�ٍϋ敪���A<BR>
     * �@@�@@null�̏ꍇ��"�M�p"�A<BR>
     * �@@�@@"1:���x�M�p"�̏ꍇ��"�M�p���N"�A<BR>
     * �@@�@@"3:��ʐM�p"�̏ꍇ��"�M�p����"�ƕ\������B<BR>
     * <BR>
     */
    public String commodityCode;
    
    /**
     * (�ٍϋ敪)<BR>
     * 1�F�@@���x�M�p<BR>
     * 3�F�@@��ʐM�p<BR>
     */
    public String repaymentDiv;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�����敪)<BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     * <BR>
     */
    public String taxType;
    
    /**
     * (�|��E�v��)<BR>
     * �|��E�v��<BR>
     */
    public String remarkName;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String quantity;
    
    /**
     * (���ʒP��)<BR>
     * ���ʒP��<BR>
     */
    public String quantityUnit;
    
    /**
     * (�P��)<BR>
     * �P��<BR>
     */
    public String price;
    
    /**
     * (�����敪)<BR>
     * 1�F����:���t,�M�p�敨:�������ԍ�<BR>
     * 2�F����:���t,�M�p�敨:�������ԍ�<BR>
     */
    public String historyDealingType;
    
    /**
     * (��n���z)<BR>
     * ��n���z<BR>
     */
    public String deliveryAmount;
    
    /**
     * (���n���v)<BR>
     * ���n���v<BR>
     */
    public String capitalProfitLoss;
    
    /**
     * (���׊Ǘ��ԍ�)<BR>
     * ���׊Ǘ��ԍ�<BR>
     * ����ʂɂ͕\�����Ȃ��B<BR>
     */
    public String detailsManageNo;
    
    /**
     * (�뉿�P�����׃����N�t���O)<BR>
     * �뉿�P�����׃����N�t���O<BR>
     * <BR>
     * false�F�@@�����N�Ȃ�<BR>
     * true�F�@@�����N����<BR>
     */
    public boolean bookValueLink;
       
    /**
     * (�o���敪)<BR>
     * �o���敪<BR>
     * �P�F�o<BR>�@@
     * �Q�F��<BR>
     */
    public String ioDiv;
    
    /**
     * (����R�[�h)<BR>
     * ����R�[�h<BR>
     * SONAR����擾<BR>
     */
    public String tradeCode;
    
    /**
     * (�E�v�R�[�h)<BR>
     * �E�v�R�[�h<BR>
     * SONAR����擾<BR>
     */
    public String remarkCode;
    
    /**
     * (�ʉݒP��)<BR>
     * �ʉݒP��<BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~  A0:US$�@@A1:C$�@@A2:A$<BR>
     * A3:HK$�@@A4:S$  A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS M0:DKr�@@M1:NKr<BR>
     * M2:SKr�@@N0:Pts�@@T0:�~  T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     */
    public String monetaryUnit;

    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪
     * <BR>
     * 0 or null�F�@@�~�݌���<BR>
     * 1�F�@@�O�݌���<BR>
     */
    public String settleDiv;

    /**
     * @@roseuid 41789C4C0167
     */
    public WEB3HistoryTradeHistoryUnit() 
    {
     
    }
}
@
