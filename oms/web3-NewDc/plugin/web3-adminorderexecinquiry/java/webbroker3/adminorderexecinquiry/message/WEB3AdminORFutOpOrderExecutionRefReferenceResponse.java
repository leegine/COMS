head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�������Ɖ�X�|���X (WEB3AdminORFutOpOrderExecutionRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�敨OP�������Ɖ�X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�敨OP�������Ɖ�X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefReferenceResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_fut_op_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (OP�o���I���^�����J�z�����敪)<BR>
     * <BR>
     * OP�o���I���^�����J�z�����敪<BR>
     * <BR>
     * 0�F�@@�o���I���ρ@@1�F�@@�����J�z������<BR>
     * 2�F�@@�����J�z������ 9�F�@@�����J�z�����G���[<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * opCarryoverEndType<BR>
     * 0: Def.NOT_STARTED_PROCESS�@@1: Def.COMPLETE_PROCESS<BR>
     * 2: Def.CALL_CARRYOVER_AP 9: Def.ERROR<BR>
     * <BR>
     */
    public String opCarryoverEndType = null;

    /**
     * (�敨�o���I���^�����J�z�����敪)<BR>
     * <BR>
     * �敨�o���I���^�����J�z�����敪<BR>
     * <BR>
     * 0�F�@@�o���I����<BR>
     * 1�F�@@�����J�z������<BR>
     * 2�F�@@�����J�z������<BR>
     * 9�F�@@�����J�z�����G���[<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * fuCarryoverEndType<BR>
     * 0: Def.NOT_STARTED_PROCESS<BR>
     * 1: Def.COMPLETE_PROCESS<BR>
     * 2: Def.CALL_CARRYOVER_AP<BR>
     * 9: Def.ERROR<BR>
     * <BR>
     */
    public String fuCarryoverEndType = null;

    /**
     * (���y�[�W��)<BR>
     * <BR>
     * ���y�[�W��<BR>
     * <BR>
     * totalPages<BR>
     * <BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * <BR>
     * �����R�[�h��<BR>
     * <BR>
     * totalRecords<BR>
     * <BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * <BR>
     * �\���y�[�W�ԍ�<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * �ڍ׉�ʏ��ꗗ<BR>
     * <BR>
     */
    public WEB3AdminORDetailDispInfoUnit[] detailDispInfoList;

    /**
     * �Ǘ��Ґ敨OP�������Ɖ�Unit�ꗗ<BR>
     * <BR>
     */
    public WEB3AdminORFutOpOrderExecutionRefUnit[] futOpeOrderExecutionRefList;

    /**
     * @@roseuid 4212FBB30111
     */
    public WEB3AdminORFutOpOrderExecutionRefReferenceResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminORFutOpOrderExecutionRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
