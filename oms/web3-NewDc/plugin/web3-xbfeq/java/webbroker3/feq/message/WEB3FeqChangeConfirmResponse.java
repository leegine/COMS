head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������m�F���X�|���X(WEB3FeqChangeConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�O�����������m�F���X�|���X)<BR>
 * �O�����������m�F���X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqChangeConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_changeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (���o������)<BR>
     * ���o������<BR>
     */
    public String partContQuantity;
    
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
     */
    public String commission;
    
    
    /**
     * (�萔�������)<BR>
     * �萔�������<BR>
     */
    public String commissionConsumptionTax;
    
    
    /**
     * @@roseuid 42CE3A07009C
     */
    public WEB3FeqChangeConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
