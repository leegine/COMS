head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�m�F���X�|���X(WEB3FeqBuyConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O���������t�m�F���X�|���X)<BR>
 * �O���������t�m�F���X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBuyConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (���t��ב�)<BR>
     * ���t��ב�<BR>
     */
    public String buyExchange;
    
    /**
     * (�T�Z��n���)<BR>
     * �T�Z��n���<BR>
     * <BR>
     * �����N�G�X�g.���ϋ敪�ɉ����āA�~��/�O�݂̒l���Z�b�g����B<BR>
     */
    public String estimatedPrice;
    
    /**
     * (���n���Z���)<BR>
     * ���n���Z���<BR>
     * <BR>
     * �����N�G�X�g.���ϋ敪�ɉ����āA�~��/�O�݂̒l���Z�b�g����B<BR>
     */
    public String localClearUpPrice;
    
    /**
     * (�萔��)<BR>
     * �萔��<BR>
     * <BR>
     * �����N�G�X�g.���ϋ敪�ɉ����āA�~��/�O�݂̒l���Z�b�g����B<BR>
     */
    public String commission;
    
    /**
     * (�萔�������)<BR>
     * �萔�������<BR>
     * <BR>
     * �����N�G�X�g.���ϋ敪�ɉ����āA�~��/�O�݂̒l���Z�b�g����B<BR>
     */
    public String commissionConsumptionTax;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �O�݂̏ꍇ�̒ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId;
    
    /**
     * (�m�F���P��)<BR>
     * �m�F���P��<BR>
     */
    public String checkPrice;
    
    /**
     * (�m�F��������)<BR>
     * �m�F��������<BR>
     */
    public Date checkDate;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 42CE3A05031C
     */
    public WEB3FeqBuyConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqBuyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
