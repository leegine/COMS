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
filename	WEB3AdminIfoDepShortageReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵏Ɖ�X�|���X(WEB3AdminIfoDepShortageReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�؋����s���󋵏Ɖ�X�|���X)<BR>
 * �Ǘ��ҁE�؋����s���󋵏Ɖ�X�|���X�N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271151L;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages = "0";

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords = "0";

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex = "0";

    /**
     * (�\������)<BR>
     * �\������<BR>
     */
    public Date dispDate;

    /**
     * (�؋����s���󋵏��ꗗ)<BR>
     * �؋����s���󋵏��ꗗ
     */
    public WEB3IfoDepShortageInfo[] ifoDepShortageInfos;

    /**
     * @@roseuid 49A7485500EA
     */
    public WEB3AdminIfoDepShortageReferenceResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminIfoDepShortageReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
