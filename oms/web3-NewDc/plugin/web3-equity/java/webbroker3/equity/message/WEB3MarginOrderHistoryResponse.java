head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p������������Ɖ�X�|���X�N���X(WEB3MarginOrderHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p������������Ɖ�X�|���X�j�B<br>
 * <br>
 * �M�p������������Ɖ�X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginOrderHistoryResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_orderHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (���������ꗗ)<BR>
     * �M�p���������藚�𖾍�<BR>
     */
    public WEB3MarginChangeCancelHistoryGroup[] changeCancelHistoryGroups;
    
    /**
     * @@roseuid 414048CB00AF
     */
    public WEB3MarginOrderHistoryResponse() 
    {
     
    }

    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOrderHistoryResponse(WEB3MarginOrderHistoryRequest l_request)
    {
        super(l_request);
    }
}
@
