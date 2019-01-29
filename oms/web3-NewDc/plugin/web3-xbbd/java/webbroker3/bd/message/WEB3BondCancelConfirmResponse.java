head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������m�F���X�|���X(WEB3BondCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ���� (���u) �V�K�쐬
Revesion History : 2007/07/25 �Ӑ� (���u) ���f��No.220
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (������m�F���X�|���X)<BR>
 * ������m�F���X�|���X
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3BondCancelConfirmResponse extends WEB3GenResponse 
{

    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_cancel_confirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    public String buySellPrice;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String coupon;
    
    /**
     * (���s��)<BR>
     * ���s��<BR>
     */
    public Date issueDate;
    
    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (�������P)<BR>
     * �������P<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (�������Q)<BR>
     * �������Q<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (���ғ�)<BR>
     * ���ғ�<BR>
     */
    public Date maturityDate;
    
    /**
     * (����敪)<BR>
     * ����敪 <BR>
     *<BR>
     *1�F���� <BR>
     *2�F���t <BR>
     *3�F���p <BR>
     */
    public String stateDiv;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪 <BR>
     * <BR>
     * 1�F�~�� <BR>
     * 2�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g <BR>
     */
    public String fxRate;
    
    /**
     * (�z�ʋ��z)<BR>
     * �z�ʋ��z<BR>
     */
    public String faceAmount;
    
    /**
     * (��������i�O�݁j)<BR>
     * ��������i�O�݁j<BR>
     */
    public String foreignTradePrice;
    
    /**
     * (��������i�~�݁j)<BR>
     * ��������i�~�݁j<BR>
     */
    public String yenTradePrice;
    
    /**
     * (�o�ߗ��q�i�O�݁j)<BR>
     * �o�ߗ��q�i�O�݁j<BR>
     */
    public String foreignAccruedInterest;
    
    /**
     * (�o�ߗ��q�i�~�݁j)<BR>
     * �o�ߗ��q�i�~�݁j<BR>
     */
    public String yenAccruedInterest;
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (��n����i�~�݁j)<BR>
     * ��n����i�~�݁j<BR>
     */
    public String yenDeliveryPrice;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public Date orderDate;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date executionUpdateDate;

    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;

    /**
     * (�Љ�敪)<BR>
     * �Љ�敪 <BR>
     * <BR>
     * �P�F���ڎ�� <BR>
     * �Q�F�P���Љ� <BR>
     * �R�F���i�Љ� <BR>
     * �S�F������<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h<BR>
     */
    public String introduceStoreCode;
    
    /**
     * (������m�F���X�|���X)<BR>
     * �R���X�g���N�^<BR> 
     */
    public WEB3BondCancelConfirmResponse()
    {
    	
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
    
}
@
