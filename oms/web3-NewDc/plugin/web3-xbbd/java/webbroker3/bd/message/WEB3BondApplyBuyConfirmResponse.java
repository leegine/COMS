head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.42.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�m�F���X�|���X(WEB3BondApplyBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (������/���t�m�F���X�|���X)<BR>
 * ������/���t�m�F���X�|���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyConfirmResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyConfirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;          
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String id;
    
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
     * (�m�F��������)<BR>
     * �m�F��������<BR>
     */
    public Date checkDate;
    
    /**
     * (������/���t�m�F���X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44C59BFA028C
     */
    public WEB3BondApplyBuyConfirmResponse() 
    {
     
    }
    
     /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondApplyBuyConfirmResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    }       
}
@
