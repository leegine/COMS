head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���M�ݓ��������Ɖ�X�|���X
                        (WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE���M�ݓ��������Ɖ�X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE���M�ݓ��������Ɖ�X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_mutual_ruito_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502011606L;

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
     * �i�Ǘ��ғ��M�ݓ��������Ɖ�Unit�ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefUnit[] mutualRuitoOrderExecutionRefList;

    /**
     * @@roseuid 4212FC2501BD
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse()
    {

    }

    /**
    *
    * @@param l_request WEB3GenRequest
    */
    public WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
