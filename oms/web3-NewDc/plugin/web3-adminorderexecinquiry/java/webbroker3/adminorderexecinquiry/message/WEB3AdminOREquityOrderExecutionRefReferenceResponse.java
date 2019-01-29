head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����������Ɖ�X�|���X�N���X (WEB3AdminOREquityOrderExecutionRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * <BR>
 * �Ǘ��ҁE�����������Ɖ�X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefReferenceResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_equity_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (�o���I���^�����J�z�����敪)<BR>
     * <BR>
     * �o���I���^�����J�z�����敪<BR>
     * <BR>
     * 0�F�@@�o���I����<BR>
     * 1�F�@@�����J�z������<BR>
     * 2�F�@@�����J�z������<BR>
     * 9�F�@@�����J�z�����G���[<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * carryoverEndType<BR>
     * <BR>
     * 0: Def.NOT_STARTED_PROCESS<BR>
     * 1: Def.COMPLETE_PROCESS<BR>
     * 2: Def.CALL_CARRYOVER_AP<BR>
     * 9: Def.ERROR<BR>
     * <BR>
     */
    public String carryoverEndType = null;

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
     * �i�Ǘ��Ҋ����������Ɖ�Unit�ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminOREquityOrderExecutionRefUnit[] equityOrderExecutionRefList;

    /**
     * �i�ڍ׉�ʏ��ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminORDetailDispInfoUnit[] detailDispInfoList;

    /**
     * @@roseuid 4212FB4C03C1
     */
    public WEB3AdminOREquityOrderExecutionRefReferenceResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOREquityOrderExecutionRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
