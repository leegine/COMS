head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�����ꗗ���N�G�X�g(WEB3MutualFixedBuyConditionListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�莞��z���t�����ꗗ���N�G�X�g)<BR>
 * ���M�莞��z���t�����ꗗ���N�G�X�g<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionListRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607221353L; 
   
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyConditionListRequest() 
    {
    }
    
    /**
     * �icreateResponse�̎����j <BR>
     * <BR>
     * ���M�莞��z���t�����ꗗ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualFixedBuyConditionListResponse(this);
    }
    
}

@
