head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l����{���Ɖ�X�|���X(WEB3AccInfoAccountBaseInfoReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l����{���Ɖ�X�|���X)<BR>
 * ���q�l����{���Ɖ�X�|���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfoReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_accountBaseInfoReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082155L;

    /**
     * (�ڋq��{���)<BR>
     * �ڋq��{���<BR>
     */
    public WEB3AccInfoAccountBaseInfo accountBaseInfo;

    /**
     * @@roseuid 418F39ED030D
     */
    public WEB3AccInfoAccountBaseInfoReferenceResponse()
    {

    }

    /**
     * (���q�l����{���Ɖ�X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@roseuid 41368E2102A6
     */
    public WEB3AccInfoAccountBaseInfoReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
