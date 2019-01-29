head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�I�v�V�����c���Ɖ�c�����v���X�|���X�N���X(WEB3FuturesOptionsBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬   
                 : 2006/07/24 ���� �d�l�ύX���f��No.526�A531 
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨�I�v�V�����c���Ɖ�c�����v���X�|���X)<BR>
 * �����w���敨�I�v�V�����c���Ɖ�c�����v���X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceTotalResponse extends WEB3GenResponse 
{
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReferenceTotal";
    
    /**
     * �v�b�g�����ʎ������z
     */
    public String putBuyCurrentPrice = "0";
    
    /**
     * �R�[�������ʎ������z
     */
    public String callBuyCurrentPrice = "0";
    
    /**
     * �����ʎ������z
     */
    public String buyCurrentPrice = "0";
    
    /**
     * �v�b�g�����ʎ������z
     */
    public String putSellCurrentPrice = "0";
    
    /**
     * �R�[�������ʎ������z
     */
    public String callSellCurrentPrice = "0";
    
    /**
     * �����ʎ������z
     */
    public String sellCurrentPrice = "0";
    
    /**
     * �v�b�g�����ʑ�����
     */
    public String putBuyTotalQuantity = "0";
    
    /**
     * �R�[�������ʑ�����
     */
    public String callBuyTotalQuantity = "0";
    
    /**
     * �����ʑ�����
     */
    public String buyTotalQuantity = "0";
    
    /**
     * �v�b�g�����ʑ�����
     */
    public String putSellTotalQuantity = "0";
    
    /**
     * �R�[�������ʑ�����
     */
    public String callSellTotalQuantity = "0";
    
    /**
     * �����ʑ�����
     */
    public String sellTotalQuantity = "0";
    
    /**
     * ���ʑ�����
     */
    public String totalQuantity = "0";
    
    /**
     * �v�b�g�����ʕ]�����v���v
     */
    public String putBuyAssetProfitLoss = "0";
    
    /**
     * �R�[�������ʕ]�����v���v
     */
    public String callBuyAssetProfitLoss = "0";
    
    /**
     * �����ʕ]�����v���v
     */
    public String buyAssetProfitLoss = "0";
    
    /**
     * �v�b�g�����ʕ]�����v���v
     */
    public String putSellAssetProfitLoss = "0";
    
    /**
     * �R�[�������ʕ]�����v���v
     */
    public String callSellAssetProfitLoss = "0";
    
    /**
     * �����ʕ]�����v���v
     */
    public String sellAssetProfitLoss = "0";
    
    /**
     * �]�����v���v
     */
    public String appraisalProfitLoss = "0";
    
    /**
     * �v�b�g�����ʕ]�����v���v(���o�)
     */
    public String putBuyAssetProfitLossCost = "0";
    
    /**
     * �R�[�������ʕ]�����v���v(���o�)
     */
    public String callBuyAssetProfitLossCost = "0";
    
    /**
     * �����ʕ]�����v���v(���o�)
     */
    public String buyAssetProfitLossCost = "0";
    
    /**
     * �v�b�g�����ʕ]�����v���v(���o�)
     */
    public String putSellAssetProfitLossCost = "0";
    
    /**
     * �R�[�������ʕ]�����v���v(���o�)
     */
    public String callSellAssetProfitLossCost = "0";
    
    /**
     * �����ʕ]�����v���v(���o�)
     */
    public String sellAssetProfitLossCost = "0";
    
    /**
     * �]�����v���v(���o�)
     */
    public String appraisalProfitLossCost = "0";
    
    /**
     * (�w���ʎc�����v)<BR>
     * �����w���敨�I�v�V�����w���ʎc�����v�̔z��<BR>
     */
    public WEB3FuturesOptionsBalRefTotalParIndexUnit[] futuresOptionsBalRefTotalParIndexUnits;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesOptionsBalanceReferenceTotalResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
