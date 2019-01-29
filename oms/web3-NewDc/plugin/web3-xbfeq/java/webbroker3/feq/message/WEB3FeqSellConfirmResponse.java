head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�m�F���X�|���X(WEB3FeqSellConfirmResponse)
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

public class WEB3FeqSellConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (���t��ב�)<BR>
     * ���t��ב�<BR>
     */
    public String sellExchange;
    
    /**
     * (�T�Z��n���)<BR>
     * �T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P��<BR>
     */
    public String estimatedBookPrice;
    
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
     * (�萔��)<BR>
     * �萔��<BR>
     * �����N�G�X�g.���ϋ敪�ɉ����āA�~��/�O�݂̒l���Z�b�g����B<BR>
     */
    public String commission;
    
    /**
     * (�萔�������)<BR>
     * �萔�������<BR>
     * �����N�G�X�g.���ϋ敪�ɉ����āA�~��/�O�݂̒l���Z�b�g����B <BR>
     */
    public String commissionConsumptionTax;
    
    /**
     * @@roseuid 42CE3A03034B
     */
    public WEB3FeqSellConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqSellConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
