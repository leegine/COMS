head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p�m�F���X�|���X(WEB3BondSellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����p�m�F���X�|���X)<BR>
 * �����p�m�F���X�|���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellConfirmResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellConfirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;  
    
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
     * (�T�Z��n����i�O�݁j)<BR>
     * �T�Z��n����i�O�݁j<BR>
     */
    public String foreignEstDeliveryPrice;
    
    /**
     * (�T�Z��n����i�~�݁j)<BR>
     * �T�Z��n����i�~�݁j<BR>
     */
    public String yenEstDeliveryPrice;
    
    /**
     * (����ID)<BR>
     * �����h�c<BR>
     */
    public String orderId;
    
    /**
     * (�m�F��������)<BR>
     * �m�F��������<BR>
     */
    public Date checkDate;
    
    /**
     * (�����p�m�F���X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D7FE880269
     */
    public WEB3BondSellConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondSellConfirmResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    }
}
@
