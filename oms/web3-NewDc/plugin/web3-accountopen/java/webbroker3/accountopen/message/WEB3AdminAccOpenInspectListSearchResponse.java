head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectListSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������X�|���X(WEB3AdminAccOpenInspectListSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/19 ���H�n�@@(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������X�|���X
 * 
 * @@author ���H�n
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectListSearchResponse extends WEB3GenResponse 
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
     * @@roseuid 44912C11037A
     */
    public WEB3AdminAccOpenInspectListSearchResponse() 
    {
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenInspectListSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
