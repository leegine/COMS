head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵏Ɖ���̓��X�|���X(WEB3AdminIfoDepShortageRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�؋����s���󋵏Ɖ���̓��X�|���X)<BR>
 * �Ǘ��ҁE�؋����s���󋵏Ɖ���̓��X�|���X�N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageRefInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271153L;

    /**
     * (�������t�ꗗ)<BR>
     * �������t�ꗗ<BR>
     */
    public Date[] searchDateList;

    /**
     * @@roseuid 49A748550261
     */
    public WEB3AdminIfoDepShortageRefInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminIfoDepShortageRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
