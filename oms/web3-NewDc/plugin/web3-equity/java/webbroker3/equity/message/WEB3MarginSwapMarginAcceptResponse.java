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
filename	WEB3MarginSwapMarginAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n��t���X�|���X(WEB3MarginSwapMarginAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i�M�p����������n��t���X�|���X�j�B<br>
 * <br>
 * �M�p����������n��t���X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptResponse extends WEB3BackResponse

{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginAccept";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * @@roseuid 41404112002A
     */
    public WEB3MarginSwapMarginAcceptResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginSwapMarginAcceptResponse(WEB3MarginSwapMarginAcceptRequest l_request)
    {
        super(l_request);
    }
}
@
