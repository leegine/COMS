head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������׃��X�|���X(WEB3HistoryTradeDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  �� �� �@@(���u) �V�K�쐬
                   2006/10/19  �����F (���u) ���f�� 057
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (������׃��X�|���X)<BR>
 * ������׃��X�|���X�N���X<BR>
 * 
 * @@author �� �� �@@
 * @@version 1.0
 */
public class WEB3HistoryTradeDetailResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221708L;
        
    /**
     * (�|��E�v��)<BR>
     * �|��E�v��<BR>
     */
    public String remarkName;
    
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
     * (�s��敪)<BR>
     * 1�F�@@����<BR>
     * 2�F�@@���<BR>
     * 3�F�@@����<BR>
     * 4�F�@@�D��<BR>
     * 5�F�@@NNM<BR>
     * 6�F�@@JASDAQ<BR>
     * 9�F�@@����<BR>
     */
    public String marketDiv;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String quantity;
    
    /**
     * (�P��)<BR>
     * �P��<BR>
     */
    public String price;
    
    /**
     * (�����敪)<BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     */
    public String taxType;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date execDate;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;
    
    /**
     * (�����z)<BR>
     * �����z<BR>
     */
    public String execAmount;
    
    /**
     * (�萔��)<BR>
     * �萔��<BR>
     */
    public String commission;
    
    /**
     * (�萔�������)<BR>
     * �萔�������<BR>
     */
    public String commissionTax;
    
    /**
     * (��n���z)<BR>
     * ��n���z<BR>
     */
    public String deliveryAmount;
    
    /**
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     */
    public String commodityCode;

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
     * (�s��敪�i�O���j)<BR>
     * �s��敪�i�O���j<BR>
     * N1�F���` N2�F�[�Z�� X1�F��C<BR>
     */
    public String fstkExchDiv;

    /**
     * (�����z�i�O�݁j)<BR>
     * �����z�i�O�݁j<BR>
     * ������6������<BR>
     */
    public String monetaryUnitExecutedAmount;

    /**
     * (���n�萔���i�O�݁j)<BR>
     * ���n�萔���i�O�݁j<BR>
     * ������6������<BR>
     */
    public String monetaryUnitComission;

    /**
     * (���n����Łi�O�݁j)<BR>
     * ���n����Łi�O�݁j<BR>
     * ������6������<BR>
     */
    public String monetaryUnitTradeTax;

    /**
     * (���̑��萔���i�O�݁j)<BR>
     * ���̑��萔���i�O�݁j<BR>
     * ������6������<BR>
     */
    public String monetaryUnitInterest;

    /**
     * (���n��n����i�O�݁j)<BR>
     * ���n��n����i�O�݁j<BR>
     * ������6������<BR>
     */
    public String localSettleAmount;
    
    /**
     * (���n��n����i�~�݁j)<BR>
     * ���n��n����i�~�݁j<BR>
     * ������6������<BR>
     */
    public String localSettleAmountYen;
    
    /**
     * (���ב�)<BR>
     * ���ב�<BR>
     * ������7������<BR>
     */
    public String execExchange;

    /**
     * (�����萔���i�~�݁j)<BR>
     * �����萔���i�~�݁j<BR>
     * ������6������<BR>
     */
    public String domesticCommission;

    /**
     * (�z��)<BR>
     * �z��<BR>
     */
    public String faceAmount;

    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0 or null �F�~�݌���<BR>
     * 1�F�O�݌���<BR>
     */
    public String settleDiv;

    /**
     * (�o�ߗ��q(�~��))<BR>
     * �o�ߗ��q(�~��)<BR>
     */
    public String yenAccruedInterest;

    /**
     * (�o�ߗ��q(�O��))<BR>
     * �o�ߗ��q(�O��)<BR>
     */
    public String foreignAccruedInterest;

    /**
     * @@roseuid 41789C4B00CB
     */
    public WEB3HistoryTradeDetailResponse() 
    {
     
    }

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3HistoryTradeDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
