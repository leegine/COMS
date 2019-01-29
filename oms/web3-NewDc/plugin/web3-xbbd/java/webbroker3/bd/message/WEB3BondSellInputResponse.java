head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p���̓��X�|���X(WEB3BondSellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����p���̓��X�|���X)<BR>
 * �����p���̓��X�|���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellInputResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellInput";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L; 
    
    /**
     * (���p�\�z�ʋ��z)<BR>
     * ���p�\�z�ʋ��z<BR>
     */
    public String sellAbleFaceAmount;
    
    /**
     * (�T�Z�]���z�i�~�݁j)<BR>
     * �T�Z�]���z�i�~�݁j<BR>
     */
    public String yenEstimatedAsset;
    
    /**
     * (�T�Z�]���z�i�O�݁j)<BR>
     * �T�Z�]���z�i�O�݁j<BR>
     */
    public String foreignEstimatedAsset;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String taxType;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (���p�i�]���j�P��)<BR>
     * ���p�i�]���j�P��<BR>
     */
    public String sellEvaluationPrice;
    
    /**
     * (���s��)<BR>
     * ���s��<BR>
     */
    public Date issueDate;
    
    /**
     * (���ғ�)<BR>
     * ���ғ�<BR>
     */
    public Date maturityDate;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (�������P)<BR>
     * �������P<BR>
     * <BR>
     * mmdd<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (�������Q)<BR>
     * �������Q<BR>
     * <BR>
     * mmdd<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String coupon;
    
    /**
     * (�\���P��)<BR>
     * �\���P��<BR>
     */
    public String tradeUnit;
    
    /**
     * (���p��ב�)<BR>
     * ���p��ב�<BR>
     */
    public String sellBaseFx;
    
    /**
     * (���ϋ敪�ꗗ)<BR>
     * ���ϋ敪�ꗗ<BR>
     * <BR>
     * 1�F�~��<BR>
     * 2�F�O��<BR>
     */
    public String[] settleDivList;
    
    /**
     * (�����p���̓��X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D7E6DC0233
     */
    public WEB3BondSellInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondSellInputResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    } 
}
@
