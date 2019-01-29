head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : ���ϖ��׃��X�|���X(WEB3HistorySettleDetailResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 ���q (���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���ϖ��׃��X�|���X)<BR>
 * ���ϖ��׃��X�|���X�N���X<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3HistorySettleDetailResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_settleDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221704L;
        
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
     * (�����敪)<BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     */
    public String taxType;
    
    /**
     * (�ٍϋ敪)<BR>
     * 1�F�@@���x�M�p<BR>
     * 3�F�@@��ʐM�p<BR>
     */
    public String repaymentDiv;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date openExecDate;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date closeExecDate;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String contractPrice;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String closeContractPrice;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;
    
    /**
     * (�����z)<BR>
     * �����z<BR>
     */
    public String contractAmount;
    
    /**
     * (�����z)<BR>
     * �����z<BR>
     */
    public String closeContractAmount;
    
    /**
     * (���萔��)<BR>
     * ���萔��<BR>
     */
    public String openCommission;
    
    /**
     * (���萔��)<BR>
     * ���萔��<BR>
     */
    public String closeCommission;
    
    /**
     * (���萔�������)<BR>
     * ���萔�������<BR>
     */
    public String openCommissionTax;
    
    /**
     * (���萔�������)<BR>
     * ���萔�������<BR>
     */
    public String closeCommissionTax;
    
    /**
     * (�����Ǘ���)<BR>
     * �����Ǘ���<BR>
     */
    public String managementFee;
    
    /**
     * (�����Ǘ�������)<BR>
     * �����Ǘ�������<BR>
     */
    public String managementFeeTax;
    
    /**
     * (���`������)<BR>
     * ���`������<BR>
     */
    public String nameTransferFee;
    
    /**
     * (���`�����������)<BR>
     * ���`�����������<BR>
     */
    public String nameTransferFeeTax;
    
    /**
     * (�݊���)<BR>
     * �݊���<BR>
     */
    public String loanEquityFee;
    
    /**
     * (�x������)<BR>
     * �x������<BR>
     */
    public String debitDailyInterest;
    
    /**
     * (�������)<BR>
     * �������<BR>
     */
    public String creditDailyInterest;
    
    /**
     * (�z����)<BR>
     * �z����<BR>
     */
    public String dividendAmount;
    
    /**
     * (�����z)<BR>
     * �����z<BR>
     */
    public String adjustAmount;
    
    /**
     * (��n���z)<BR>
     * ��n���z<BR>
     */
    public String deliveryAmount;
    
    /**
     * @@roseuid 41789C4903C8
     */
    public WEB3HistorySettleDetailResponse() 
    {
     
    }

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3HistorySettleDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
