head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondListInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������\��ꗗ���̓��X�|���X(WEB3AdminPMProductCondListInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������������\��ꗗ���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�������������\��ꗗ���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondListInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondListInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_list_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i���ݓ����j<BR>
     * <BR>
     * ���ݓ���<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * �i���ڋ敪���ꗗ�j<BR>
     * <BR>
     * ���ڋ敪���ꗗ
     * <BR>
     * itemInfoList<BR>
     * <BR>
     */
    public WEB3AdminPMItemInfoUnit[] itemInfoList;

    /**
     * @@roseuid 41FD92D200BB
     */
    public WEB3AdminPMProductCondListInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondListInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
