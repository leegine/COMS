head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectListSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g(WEB3AdminAccOpenInspectListSearchRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/19 ���H�n�@@(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g
 * 
 * @@author ���H�n
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectListSearchRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_AccOpen_inspectListSearch";
    
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200606151150L;
    
    /**
     * @@roseuid 44912C11029F
     */
    public WEB3AdminAccOpenInspectListSearchRequest() 
    {
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenInspectListSearchResponse(this);
    }

}
@
