head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������V�K�o�^�m�F���X�|���X(WEB3AdminOffFloorRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������V�K�o�^�m�F���X�|���X)<BR>
 * <BR>
 * �Ǘ��җ���O���������V�K�o�^�T�[�r�X�i�m�F�j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * -----<English>--------<BR>
 * <BR>
 * WEB3AdminOffFloorRegistConfirmResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorRegistService(validate)<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorRegistConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_regist_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (������)<BR>
     * <BR>
     * �������B<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * (��t�I������)<BR>
     * <BR>
     * ��t�I�������B<BR>
     * <BR>
     * orderEndDatetime<BR>
     * <BR>
     */
    public Date orderEndDatetime;

    /**
     * @@roseuid 421AE47F0012
     */
    public WEB3AdminOffFloorRegistConfirmResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
