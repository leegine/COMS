head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨���������Ɖ�X�|���X�N���X(WEB3FuturesOrderHistoryResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/08/06 ���Ō� (���u) Review�C��
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨���������Ɖ�X�|���X)<BR>
 * �����w���敨���������Ɖ�X�|���X�N���X
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesOrderHistoryResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_orderHistory";
        
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407221013L;
    /**
     * (���������ꗗ�s)
     */
    public webbroker3.ifo.message.WEB3FuturesChangeCancelHistoryGroup[] futChangeCancelHistoryGroups;
    
    /**
     * @@roseuid 40F7AE0E001F
     */
    public WEB3FuturesOrderHistoryResponse() 
    {
     
    }
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�敨�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesOrderHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
