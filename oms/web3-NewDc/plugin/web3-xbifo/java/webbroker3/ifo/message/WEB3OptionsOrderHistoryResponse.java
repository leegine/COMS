head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�������������Ɖ�X�|���X(WEB3OptionsOrderHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�������������Ɖ�X�|���X)<BR>
 * �����w���I�v�V�������������Ɖ�X�|���X�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsOrderHistoryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderHistory";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141405L;

    /**
     * ���������ꗗ�s
     */
    public WEB3OptionsChangeCancelHistoryGroup[] opChangeCancelHistoryGroups;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsOrderHistoryResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsOrderHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@