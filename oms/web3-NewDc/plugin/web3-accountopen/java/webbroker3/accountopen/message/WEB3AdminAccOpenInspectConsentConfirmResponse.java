head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectConsentConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR�����F�m�F���X�|���X(WEB3AdminAccOpenInspectConsentConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 ���H�n�@@(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ҍ����J�ݐR�����F�m�F���X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݐR�����F�m�F���X�|���X
 * 
 * @@author ���H�n
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectConsentConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_AccOpen_inspectConsentConfirm";
    
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200606151150L;
    
    /**
     * (�����J�ݐR�����)<BR>
     */
    public WEB3AccOpenInspectInfo[] accopenInspectList;
    
    /**
     * @@roseuid 44912C10006D
     */
    public WEB3AdminAccOpenInspectConsentConfirmResponse() 
    {
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenInspectConsentConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
