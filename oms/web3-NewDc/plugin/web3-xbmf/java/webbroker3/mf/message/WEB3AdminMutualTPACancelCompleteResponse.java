head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���Ǘ��җ]�͒�������������X�|���X(WEB3AdminMutualTPACancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 *(�����M���Ǘ��җ]�͒�������������X�|���X)<BR>
 *�����M���Ǘ��җ]�͒�������������X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3AdminMutualTPACancelCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tpa_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191632L;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualTPACancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
