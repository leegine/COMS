head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.41.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�m�F���N�G�X�g(WEB3BondApplyBuyConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (������/���t�m�F���N�G�X�g)<BR>
 * ������/���t�m�F���N�G�X�g<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyConfirmRequest extends WEB3BondApplyBuyCommonRequest 
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
     * @@roseuid 44FBFD39004E
     */
    public WEB3BondApplyBuyConfirmRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */ 
    public WEB3GenResponse createResponse() 
    {   
        return new WEB3BondApplyBuyConfirmResponse(this);   
    }   
}
@
