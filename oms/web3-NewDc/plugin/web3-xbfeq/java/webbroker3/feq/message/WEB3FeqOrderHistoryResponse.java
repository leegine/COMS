head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������藚�����X�|���X(WEB3FeqOrderHistoryResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O������������藚�����X�|���X)<BR>
 * �O������������藚�����X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqOrderHistoryResponse extends WEB3GenResponse 
{   
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_orderHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (���������ꗗ)<BR>
     * �O������������藚�𖾍ׂ̔z��<BR>
     */
    public WEB3FeqChangeCancelHistoryGroup[] changeCancelHistoryGroups;
    
    /**
     * @@roseuid 42CE3A090251
     */
    public WEB3FeqOrderHistoryResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqOrderHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
