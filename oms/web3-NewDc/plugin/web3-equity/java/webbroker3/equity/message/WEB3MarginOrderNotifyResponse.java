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
filename	WEB3MarginOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p��������ʒm���X�|���X�N���X(WEB3MarginOrderNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i�M�p��������ʒm���X�|���X�j�B<br>
 * <br>
 * �M�p��������ʒm���X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyResponse extends WEB3BackResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_orderNotify";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * @@roseuid 414037850146
     */
    public WEB3MarginOrderNotifyResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOrderNotifyResponse(WEB3MarginOrderNotifyRequest l_request)
    {
       super(l_request);
    }
}
@
